package haverford.therapy_assistant.data.maybe;

import java.io.Serializable;
import java.util.Optional;

public abstract class Maybe<E> implements Serializable {

    public abstract Optional<E> getValue();

}
