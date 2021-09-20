import java.util.ArrayList;
import java.util.List;

public class MaxHeap implements Heap {
        private final List<HeapElement> maxHeap;
        int heapSize, minValue, maxCalue;

        // ...
        private void toggleDown(int elementIndex) {
                double key = maxHeap.get(elementIndex - 1).getKey();
                boolean wrongOrder = (key < getElementKey(elementIndex * 2))
                                || (key < getElementKey(Math.min(elementIndex * 2, maxHeap.size())));
                while ((2 * elementIndex <= maxHeap.size()) && wrongOrder) {
                        if ((2 * elementIndex < maxHeap.size())
                                        && (getElementKey(elementIndex * 2 + 1) > getElementKey(elementIndex * 2))) {
                                swap(elementIndex, 2 * elementIndex + 1);
                                elementIndex = 2 * elementIndex + 1;
                        } else {
                                swap(elementIndex, 2 * elementIndex);
                                elementIndex = 2 * elementIndex;
                        }
                        wrongOrder = (key < getElementKey(elementIndex * 2))
                                        || (key < getElementKey(Math.min(elementIndex * 2, maxHeap.size())));
                }
        }
        // ...
}