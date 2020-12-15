package br.ufes.gestao.imagem.observer;

public interface IObservado {

    public void attachObserver(IObservador observer);
    public void detachObserver(IObservador observer);
    public void notifyObservers();
    
}
