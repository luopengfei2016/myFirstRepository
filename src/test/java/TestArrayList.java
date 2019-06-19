/**
 * @author Jackson
 * @description ArrayList底层实现测试
 * @date 2019/6/18
 */
public class TestArrayList {

    private Object[] elementData;
    private int size;

    public TestArrayList() {
        this(10);
    }

    public TestArrayList(int initialcapacity) {
        if (initialcapacity < 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        elementData = new Object[initialcapacity];
    }

    public void add(Object object) {
        //数组扩容
        if (size > elementData.length) {
            Object[] newArray = new Object[size * 2];
            System.arraycopy(elementData, 0, newArray, 0, elementData.length);
            elementData = newArray;
        }
        elementData[size++] = object;
    }
}
