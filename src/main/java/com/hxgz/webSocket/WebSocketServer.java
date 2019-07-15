package com.hxgz.webSocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hxgz.config.Result;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jackson
 * @description 测试webSocket
 * @date 2019/6/16
 */
@ServerEndpoint("/webSocket/{userId}")
@Component
public class WebSocketServer {

    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //使用map对象，便于根据userId来获取对应的WebSocket
    private static ConcurrentHashMap<String, WebSocketServer> websocketList = new ConcurrentHashMap<>();

    //接收userId
    private String userId = "";

    //连接建立成功时调用
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        websocketList.put(userId, this);
        addOnlineCount();
        log.info("有新窗口在线监听：" + userId + ",当前在线人数为：" + getOnlineCount());
        this.userId = userId;
        try {
            sendMessage(JSON.toJSONString(new Result(true, 200, "连接成功")));
        } catch (IOException e) {
            e.printStackTrace();
            log.info("webSocket连接 IO异常");
        }
    }

    /**
     * @param [message]
     * @return void
     * @author Jackson
     * @description //TODO 实现服务器端主动推送消息
     * @date 2019/6/16
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    //接收客户端发送过来的消息
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自客户端"+userId+"的信息："+message);
        if(StringUtils.isNotEmpty(message)){
            JSONArray list = JSONArray.parseArray(message);
            for (int i = 0; i < list.size(); i++) {
                //解析客户端发送的报文
                JSONObject object = list.getJSONObject(i);
                String toUserId = object.getString("toUserId");
                String contentText = object.getString("contentText");
                object.put("fromUserId",this.userId);

                //发给对应用户的webSocket
                if(StringUtils.isNotEmpty(toUserId)&&StringUtils.isNotEmpty(contentText)){
                    WebSocketServer socket = websocketList.get(toUserId);
                    if(null!=socket){
                        try {
                            socket.sendMessage(JSON.toJSONString(new Result(true,200,"发送成功",object)));
                            //此处可以放置相关业务代码，例如存储到数据库
                            //.............
                            //.............
                        } catch (IOException e) {
                            e.printStackTrace();
                            log.info("webSocket IO异常");
                        }
                    }
                }
            }
        }
    }

    //发生错误时调用
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    //连接关闭时调用
    @OnClose
    public void out(Session session, CloseReason closeReason) {
        if(null!=websocketList.get(this.userId)){
            websocketList.remove(this.userId);
            reduceOnlineCount();
            log.info("有客户端下线，当前连接人数为："+getOnlineCount());
        }
    }


    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    private static synchronized void reduceOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
