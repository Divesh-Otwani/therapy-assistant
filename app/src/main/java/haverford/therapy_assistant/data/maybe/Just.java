package haverford.therapy_assistant.data.maybe;

import java.io.Serializable;
import java.util.Optional;

public class Just<E> extends Maybe<E> implements Serializable{
    private E mE;

    public Just(E e){
        mE = e;
    }

    @Override
    public Optional<E> getValue() {
        return Optional.of(mE);
    }

    public E getE(){ return mE; }
}
