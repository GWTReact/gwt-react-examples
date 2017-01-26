package gwt.react.mobx.todo.client.state;

import gwt.interop.utils.client.collections.JsArray;
import gwt.interop.utils.shared.collections.Array;
import gwt.mobx.client.MobX;
import gwt.mobx.client.MobX.*;
import gwt.mobx.client.ObservableArray;

public class AppState {
    public enum FilterStatus {
        ShowAll, ShowActive, ShowCompleted
    }

    private ObservableValue<FilterStatus> filter = MobX.observableValue(FilterStatus.ShowAll);
    private ObservableArray<TodoDO> todos = MobX.observable(JsArray.create());

    private ComputedValue<Array<TodoDO>> visibleTodos = MobX.computed(() -> {
        return todos.filter((todo) -> {
            switch(filter.get()) {
                case ShowActive: return !todo.completed;
                case ShowCompleted: return todo.completed;
                default:
                	break;
            }
            return true;
        });
    });

    private TodoDO findTodo(int id) {
        return todos.find((todo) -> todo.id == id);
    }

    public TodoDO addTodo(String text){
        TodoDO newTodo;
        newTodo = TodoDO.make(todos.getLength(), text, false);

        MobX.runInAction(() -> todos.unshift(newTodo));

        return newTodo;
    }

    public void toggleTodo(int id) {
        MobX.runInAction("toggleTodo", () -> {
            TodoDO editTodo = findTodo(id);
            editTodo.completed = !editTodo.completed;
        });
    }

    public void setFilter(FilterStatus filter) {
        MobX.runInAction("setFilter", () -> this.filter.set(filter));
    }

    public FilterStatus getFilter() {
        return filter.get();
    }

    public Array<TodoDO> getVisibleTodos() {
        return visibleTodos.get();
    }
}
