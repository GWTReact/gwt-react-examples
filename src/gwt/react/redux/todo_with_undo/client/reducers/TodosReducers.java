package gwt.react.redux.todo_with_undo.client.reducers;

import gwt.interop.utils.client.collections.JsArray;
import gwt.interop.utils.shared.collections.Array;
import gwt.react.redux.todo_with_undo.client.actions.Actions;
import gwt.react.redux.todo_with_undo.client.actions.Actions.TodoAction;
import gwt.redux.client.Reducer;
import gwt.redux.client.addons.redux_undo.ReduxUndo;
import jsinterop.annotations.JsType;

public class TodosReducers {

    @JsType
    public static class TodoState {
        public int id;
        public String text;
        public boolean completed;

        TodoState(int id, String text, boolean completed) {
            this.id = id;
            this.text = text;
            this.completed = completed;
        }
    }

    private static TodoState todo(TodoState state, TodoAction action) {

        switch (action.type) {
            case Actions.ADD_TODO:
                state = new TodoState(action.id, action.text, false);
                break;

            case Actions.TOGGLE_TODO:
                if (state.id == action.id) {
                    state = new TodoState(state.id, state.text, !state.completed);
                }
        }

        return state;
    }

    private static Reducer<Array<TodoState>, TodoAction> _todos = (state, action) -> {
        if (state == null)
            state = JsArray.create();

        switch (action.type) {
            case Actions.ADD_TODO:
                state = state.concatValue(todo(null, action));
                break;

            case Actions.TOGGLE_TODO:
                state = state.map((v, index, theArray) -> todo(v, action));
        }
        return state;
    };

    static Reducer<Array<TodoState>, TodoAction> todos = ReduxUndo.undoable(_todos);
}
