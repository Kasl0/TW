package Lab3.Zad3;

public interface IForks {
    void get(int philosopher_number) throws InterruptedException;
    void give_back(int philosopher_number);
}
