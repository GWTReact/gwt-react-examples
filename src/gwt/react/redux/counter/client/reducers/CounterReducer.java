package gwt.react.redux.counter.client.reducers;

import gwt.redux.client.Action;

public class CounterReducer {
    public static Integer counter(Integer state, Action action)  {
        if (state == null) {
            state = 0;
        }

        switch(action.type) {
            case "INCREMENT":
                state = state + 1;
                break;

            case "DECREMENT":
                state = state - 1;
        }

        return state;
    }
}