import java.util.ArrayList;
import java.util.Comparator;

//Реализовать класс, представляющий паттерн Object Pool:
//        § операции: get – взять элемент из пула в эксклюзивное пользование, release – вернуть элемент в пул
//        § release не должен принимать "чужие" объекты
//        § generic

public class testClass<T>{
    private ArrayList<T> listOfObjects = new ArrayList<>();
    private ArrayList<Boolean> objectsState = new ArrayList<>();
    {
        for (int i = 0; i < listOfObjects.size(); i++) {
            objectsState.add(true);
        }
    }

    public T get() {
        T freeObject = null;
        for (int i = 0; i < objectsState.size(); i++) {
            if (objectsState.get(i)) {
                freeObject = listOfObjects.get(i);
                break;
            }
        }
        return freeObject;
    }

    public boolean release(T releasedObject) {
        if (listOfObjects.contains(releasedObject)) {
            for (int i = 0; i < listOfObjects.size(); i++) {
                if (listOfObjects.get(i).equals(releasedObject)) {
                    objectsState.set(i, true);
                    return true;
                }
            }
        }

    }



}
