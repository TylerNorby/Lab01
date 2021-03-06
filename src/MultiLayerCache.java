public class MultiLayerCache<T> {

    private Cache<T> layerOne;
    private Cache<T> layerTwo;

    public MultiLayerCache(int cap1, int cap2) {
        layerOne = new Cache<T>(cap1);
        layerTwo = new Cache<T>(cap2);
    }

    public void cacheSearch(T data) {
        if (layerOne.find(data) != null) {
            layerOne.move(data);
        } else {
            if (layerTwo.find(data) != null) {
                layerTwo.move(data);
                layerOne.add(data);
            } else {
                layerOne.add(data);
                T temp = layerTwo.add(data);
                if (temp != null) {
                    layerOne.remove(temp);
                }
            }
        }
    }

    public void setLayerOne(Cache<T> layerOne) {
        this.layerOne = layerOne;
    }

    public void setLayerTwo(Cache<T> layerTwo) {
        this.layerTwo = layerTwo;
    }

    public Cache<T> getLayerOne() {
        return layerOne;
    }

    public Cache<T> getLayerTwo() {
        return layerTwo;
    }
}
