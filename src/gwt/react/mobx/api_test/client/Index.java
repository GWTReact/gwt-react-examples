package gwt.react.mobx.api_test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;

public class Index implements EntryPoint{

    @Override
    public void onModuleLoad() {
        new MobXTests().run();
        new ObservableMapTests().run();
        Window.alert("All tests passed");
    }
}
