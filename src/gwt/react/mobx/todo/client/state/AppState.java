package gwt.react.mobx.todo.client.state;

import gwt.interop.utils.client.collections.JsArray;
import gwt.interop.utils.shared.collections.Array;
import gwt.interop.utils.client.plainobjects.JsPlainObj;
import gwt.mobx.client.MobX;
import gwt.mobx.client.MobX.*;
import gwt.mobx.client.ObservableArray;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

public class AppState {
    public enum FilterStatus {
        ShowAll, ShowActive, ShowCompleted
    }

    //To be automatically observable, we need to define the Todo data object as an
    //object literal. If we don't do this, we would have to define each field value as
    //an ObservableValue
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
    public static class Todo extends JsPlainObj {
        public int id;
        public String text;
        public boolean completed;

        @JsOverlay
        static Todo make(int id, String text, boolean completed) {
            Todo t = new Todo();
            t.id = id;
            t.text = text;
            t.completed = completed;
            return t;
        }
    }

    private ObservableValue<FilterStatus> filter = MobX.observableValue(FilterStatus.ShowAll);
    private ObservableArray<Todo> todos = MobX.observable(JsArray.create());

    private ComputedValue<Array<Todo>> visibleTodos = MobX.computed(() -> todos.filter((todo) -> {
        switch(filter.get()) {
            case ShowActive: return !todo.completed;
            case ShowCompleted: return todo.completed;
        }
        return true;
    }));

    private Todo findTodo(int id) {
        return todos.find((todo) -> todo.id == id);
    }

    public Todo addTodo(String text){
        Todo newTodo = Todo.make(todos.getLength(), text, false);

        todos.unshift(newTodo);
        return newTodo;
    }

    public void toggleTodo(int id) {
        Todo editTodo = findTodo(id);
        editTodo.completed = !editTodo.completed;
    }

    public void setFilter(FilterStatus filter) {
        this.filter.set(filter);
    }

    public FilterStatus getFilter() {
        return filter.get();
    }

    public Array<Todo> getVisibleTodos() {
        return visibleTodos.get();
    }
}
