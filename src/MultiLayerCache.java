public class MultiLayerCache<T> {

    private Cache<T> layerOne;
    private Cache<T> layerTwo;

    public MultiLayerCache(int cap1, int cap2) {
        layerOne = new Cache<T>(cap1);
        layerTwo = new Cache<T>(cap2);
    }

    public void cacheSearch(T data) {
        if (layerOne.find(data, true) != null) {
            layerOne.move(data);
            layerTwo.move(data);
        } else {
            if (layerTwo.find(data, true) != null) {
                layerTwo.move(data);
                layerOne.add(data);
            } else {
                T temp = layerTwo.add(data);
                if (temp != null) {
                    layerOne.remove(temp);
                }

                layerOne.add(data);
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
