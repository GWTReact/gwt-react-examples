package gwt.react.widget_interop.client;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;
import gwt.react.client.api.ReactDOM;
import gwt.react.client.elements.ReactElement;


public class ReactPanel extends Widget {
    DivElement container;
    ReactElement elementToRender;

    public ReactPanel(ReactElement elementToRender) {
        this.elementToRender = elementToRender;
        container = Document.get().createDivElement();
        setElement(container);
    }

    @Override
    public void onAttach() {
        super.onAttach();
        ReactDOM.render(elementToRender, container);

    }

    @Override
    public void onDetach() {
        ReactDOM.unmountComponentAtNode(container);
        super.onDetach();
    }
}
