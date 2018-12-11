package haverford.therapy_assistant.data.maybe;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Optional;

public class Nothing<E> extends Maybe<E> implements Serializable {

    public Nothing(){}

    @Override
    public Optional<E> getValue() {
        return Optional.empty();
    }
}
