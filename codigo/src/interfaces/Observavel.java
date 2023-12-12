package src.interfaces;

public interface Observavel {
    public void addObserver(Observador observador);
    public void removeObserver(Observador observador);
    public void notificarObservadores();
}
