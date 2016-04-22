package gwt.react.redux.todo_with_undo.client.reducers;

import gwt.react.redux.todo_with_undo.client.actions.Actions;
import gwt.redux.client.Reducer;

class VisibilityFilterReducer {

    static Reducer<String, Actions.VisibilityAction> visibilityFilter = (state, action) -> {
        if (state == null)
            state = "SHOW_ALL";

        switch (action.type) {
            case Actions.SET_VISIBILITY_FILTER:
                return action.filter;

            default:
                return state;
        }
    };
}
