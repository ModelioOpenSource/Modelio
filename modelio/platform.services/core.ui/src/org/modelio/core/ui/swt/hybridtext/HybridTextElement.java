/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.core.ui.swt.hybridtext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.DefaultToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.PluginTransfer;
import org.modelio.api.ui.dnd.ModelElementTransfer;
import org.modelio.app.core.picking.IModelioPickingService;
import org.modelio.app.core.picking.IPickingClient;
import org.modelio.app.core.picking.IPickingSession;
import org.modelio.core.ui.plugin.CoreUi;
import org.modelio.core.ui.swt.textelement.PopupChooser;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.model.search.engine.searchers.model.ModelSearchCriteria;
import org.modelio.model.search.engine.searchers.model.ModelSearchEngine;
import org.modelio.ui.UIImages;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * TextElement is a reusable component wrapping an SWT Text widget that can be
 * used to edit/select a single model element.<br/>
 * The following features are provided:
 * <ul>
 * <li>auto-completion by name with selection in a popup list when several
 * elements are matching</li>
 * <li>picking of an element in the model</li>
 * <li>dropping of an element from the model</li>
 * </ul>
 * Each of these supported features has to be activated explicitly.
 * 
 * The TextElement can be configured to accept (and propose) only certain
 * elements on the following criteria:
 * <ul>
 * <li>accept null values</li>
 * <li>accept only certain metaclasses</i>
 * <li>accept only elements matching a given filter</li>
 * </ul>
 * 
 * Note: TextElement wraps a SWT Text because inheriting from Text is not
 * possible. Therefore the getTextControl() method is available to reach the
 * inner Text field, typically for layout purposes.
 */
@objid ("accf636b-8e40-4a1f-94c0-0b99cea21c7c")
public class HybridTextElement {
    /**
     * Indicates that this TextElement should accept and propose null value
     */
    @mdl.prop
    @objid ("9e3e5488-78b8-4047-a758-a47f849fb216")
    private boolean acceptNullValue;

    @mdl.propgetter
    public boolean isAcceptNullValue() {
        // Automatically generated method. Please do not modify this code.
        return this.acceptNullValue;
    }

    @mdl.propsetter
    public void setAcceptNullValue(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.acceptNullValue = value;
    }

    @objid ("e90cd5b6-5930-4674-8431-95b97d24d71f")
    private boolean acceptStringValue;

    /**
     * Accepted metaclasses
     */
    @objid ("b0dec2ad-70c5-442d-bad2-e7949be769d0")
    private final List<MClass> metaclasses = new ArrayList<>();

    /**
     * MObject filter
     */
    @objid ("70d23691-4156-4085-bda7-262c4efa3a9e")
    private IMObjectFilter filter;

    /**
     * The wrapped Text widget
     */
    @objid ("591b258f-7c3b-4eba-bbeb-9a5676e59fcf")
    private final Text text;

    /**
     * Current value of the editor, ie either the initial value or the lastly
     * validated one
     */
    @objid ("525b9921-b0c2-4628-8071-f4449b819438")
    private Object value;

    /**
     * Chosen value for the editor.<br/>
     * This element was chosen by the end-user and will become the editor value
     * on validate(true)
     */
    @objid ("89199cca-7839-4d05-b680-d8fe03db06f8")
    private Object selected;

    @objid ("924507ea-7111-41fe-9043-4916dbdf68ad")
    protected DefaultToolTip tooltip;

    /**
     * The TextElement registered listeners
     */
    @objid ("92a8ed0e-0188-4d32-bd9a-678330da8782")
    private final List<IHybridTextElementSelectionListener> listeners = new ArrayList<>(1);

    /**
     * The the completion mechanism implementation.
     */
    @objid ("dcf400b5-7684-4363-a889-8aeebde6bad3")
    private HybridCompletionDriver completionDriver = null;

    /**
     * The picking mechanism implementation
     */
    @objid ("ee122bcf-5ee4-4885-8ca2-4422d18792a4")
    private HybridPickingDriver pickingDriver = null;

    /**
     * Create a TextElement. The internal Text control is created with 'parent'
     * and 'style'. The created TextElement:
     * <ul>
     * <li>accepts null values by default.</li>
     * <li>accepts no metaclass</li>
     * <li>has no additional filter</li>
     * <li>does not provide auto-completion</li>
     * <li>does not support picking</li>
     * <li>does not support DnD</li>
     * </ul>
     * @param parent
     * @param style
     */
    @objid ("a9c26260-49a6-4087-bbf4-ad2371ed002a")
    public HybridTextElement(Composite parent, int style) {
        this.text = createControl(parent, style);
        this.acceptNullValue = true;
    }

    /**
     * Set a filter that will be used to accept (and propose) elements.
     * @param filter
     */
    @objid ("4fd910a6-30fa-41a6-97b6-bf57eca7708a")
    public void setFilter(IMObjectFilter filter) {
        this.filter = filter;
    }

    /**
     * @return the current filter
     */
    @objid ("fa832618-93f1-44e5-8c15-b0607317c00d")
    public IMObjectFilter getFilter() {
        return this.filter;
    }

    /**
     * Get the list of the accepted metaclasses for the elements. Add
     * metaclasses to this list to complete it.
     */
    @objid ("1b1d9348-1f40-45bb-a491-703298dbd4b6")
    public List<MClass> getAcceptedMetaclasses() {
        return this.metaclasses;
    }

    /**
     * Activate completion.
     */
    @objid ("2ecb0ae5-1243-4661-9bdc-9beb467549da")
    public void activateCompletion(ICoreSession session) {
        if (session != null) {
            this.completionDriver = new HybridCompletionDriver(this, (CoreSession) session);
        } else {
            if (this.completionDriver != null) {
                this.completionDriver.terminate();
                this.completionDriver = null;
            }
        }
    }

    /**
     * Activate picking.
     * @param pickingService
     */
    @objid ("42ed7f66-4015-447b-8cdf-ae4034eacbc8")
    public void activatePicking(IModelioPickingService pickingService) {
        if (pickingService != null) {
            this.pickingDriver = new HybridPickingDriver(this, pickingService);
        } else {
            if (this.pickingDriver != null) {
                this.pickingDriver.terminate();
                this.pickingDriver = null;
            }
        }
    }

    /**
     * Activate drag and drop
     * @param session
     */
    @objid ("643741f1-d7b7-427b-9f42-ae0b9c63189a")
    public void activateDragAndDrop(ICoreSession session) {
        HybridDropListener dropListener = new HybridDropListener(this, session);
        int operations = DND.DROP_MOVE | DND.DROP_COPY;
        Transfer[] types = new Transfer[] { ModelElementTransfer.getInstance(), PluginTransfer.getInstance() };
        DropTarget target = new DropTarget(this.text, operations);
        target.setTransfer(types);
        target.addDropListener(dropListener);
    }

    @objid ("d0841f11-82de-4267-b3f6-adad8dcf6e44")
    public void addListener(final IHybridTextElementSelectionListener listener) {
        this.listeners.add(listener);
    }

    @objid ("4bd300f5-d5ad-4a0c-bec3-7eb447e2a424")
    public void setValue(Object value) {
        this.value = value;
        if (value == null) {
            this.text.setText("");
        } else if (value instanceof MObject) {
            this.text.setText(((MObject) value).getName());
        } else {
            this.text.setText(value.toString());
        }
    }

    @objid ("4b7ef2df-e5a0-4926-978e-4a45bd62a8c3")
    public Object getValue() {
        return this.value;
    }

    /**
     * Returns the internal text control. Should be used only for setting layout
     * data.
     */
    @objid ("9a80ce82-543b-48d3-8fc0-5401693289a3")
    public Text getTextControl() {
        return this.text;
    }

    @objid ("8c2c9f66-2209-4e73-97a0-815f24dc782f")
    public void removeListener(final IHybridTextElementSelectionListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Create and configure the wrapped text control
     * @param parent
     * @param style
     * 
     * @return the configured Text control
     */
    @objid ("a029aab4-253d-44ce-a3f8-3818cbd9ebdc")
    private Text createControl(Composite parent, int style) {
        final Text wrappedText = new Text(parent, style);
        wrappedText.addPaintListener(new HybridTextElementPaintListener());
        
        this.tooltip = new DefaultToolTip(wrappedText) {
            @Override
            protected String getText(Event event) {
                return HybridTextElement.this.getToolTipText();
            }
        };
        return wrappedText;
    }

    @objid ("0adea1eb-201b-48bc-97b0-bc785ac257d6")
    private void fireSelectedElementChanged(final Object oldElement, final Object newElement) {
        for (final IHybridTextElementSelectionListener listener : this.listeners) {
            listener.selectedElementChanged(oldElement, newElement);
        }
    }

    /**
     * @return
     */
    @objid ("aed20851-0af1-45b1-b71a-1528554e05fc")
    private String getToolTipText() {
        final StringBuffer helpTooltip = new StringBuffer();
        
        final String indent = "\n    ";
        helpTooltip.append(CoreUi.I18N.getString("TextElement.AcceptedTypes"));
        
        if (this.acceptStringValue) {
            helpTooltip.append(indent);
            helpTooltip.append("String");
        }
        
        for (final MClass clazz : this.metaclasses) {
            helpTooltip.append(indent);
            helpTooltip.append(clazz.getName());
        }
        
        if (this.completionDriver != null) {
            helpTooltip.append("\n\n");
            helpTooltip.append(CoreUi.I18N.getString("TextElement.CompletionUsage"));
        }
        return helpTooltip.toString();
    }

    @objid ("2ed167e8-64ef-44fb-89ad-d0d69a44f816")
    protected void setSelectedElement(Object value) {
        this.selected = value;
        
        // If the element is not null, display its name in the text field
        if (value instanceof MObject) {
            MObject element = (MObject) value;
            String textString = element.getName();
            if (element.getCompositionOwner() != null) {
                textString = textString + "  (from " + element.getCompositionOwner().getName() + ")";
            }
            // update text and data
            if (!this.text.isDisposed()) {
                this.text.setText(textString);
            }
        } else if (value == null && ! isAcceptNullValue()) {
            validate(false);
            return;
        }
        validate(true);
    }

    @objid ("90bbe68a-a373-4e4a-add2-ab921fab7589")
    protected void validate(boolean save) {
        if (save) {
            // Update the data model from the content of the text field.
            final Object oldElement = this.value;
            this.value = this.selected;
            fireSelectedElementChanged(oldElement, this.value);
        }
        
        // Close the tooltip, to avoid an exception with the dispose of the text
        if (this.tooltip != null) {
            this.tooltip.hide();
            this.tooltip = null;
        }
    }

    @objid ("997793ce-91ed-4294-8e43-e51fa9abb834")
    public boolean isAcceptStringValue() {
        return this.acceptStringValue;
    }

    @objid ("0fc78722-9757-4cf6-a8f3-ecc4a30257e8")
    public void setAcceptStringValue(boolean acceptStringType) {
        this.acceptStringValue = acceptStringType;
    }

    @objid ("096c78bd-e7ed-40f7-b524-7352329f748e")
    protected DefaultToolTip getTooltip() {
        return this.tooltip;
    }

    /**
     * Wrapped text decorator. Paints a blue border around the text along with
     * an 'field assist' icon.
     * 
     * @author phv
     */
    @objid ("b4f166cd-1212-4c16-a940-8c6a4b6f7523")
    private static class HybridTextElementPaintListener implements PaintListener {
        @objid ("a96cf5cd-4e0c-4638-84fd-ea855a167db3")
        @Override
        public void paintControl(PaintEvent e) {
            final GC gc = e.gc;
            final Rectangle oldClip = gc.getClipping();
            final Rectangle clip = new Rectangle(e.x, e.y, e.width, e.height);
            final Rectangle textBounds = ((Text) e.getSource()).getBounds();
            textBounds.x = 1;
            textBounds.y = 1;
            textBounds.height = textBounds.height - 2;
            textBounds.width = textBounds.width - 2;
            
            if (((Text) e.getSource()).isFocusControl()) {
                final Color color = Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
                gc.setForeground(color);
                gc.setClipping(clip);
                gc.drawRectangle(textBounds);
                gc.setClipping(oldClip);
            }
            
            
            final Rectangle imageRect = UIImages.ASSIST.getBounds();
            gc.drawImage(UIImages.ASSIST, textBounds.x + textBounds.width - imageRect.width, textBounds.y);
        }

        @objid ("797dd9cc-a936-4d66-a234-d55ffd603a43")
        public HybridTextElementPaintListener() {
            // nothing to do
        }

    }

    /**
     * Completion driver. Search model elements matching the current text and
     * the configured completion criteria and propose them to user's choice.
     * 
     * @author phv
     */
    @objid ("b604d638-8561-4bf5-b8c0-b662b1c44682")
    private class HybridCompletionDriver {
        @objid ("15752b1c-0a1f-4dc7-92ad-37831a052180")
        private final ModelSearchCriteria searchCriteria;

        @objid ("cf7f05c8-28c9-442d-a83e-f85620b40cff")
        private final ModelSearchEngine searcher;

        @objid ("bbabafaf-b832-441d-b83a-0e6556b3662d")
        private CoreSession session;

        @objid ("87b752c9-08b4-4a3b-8395-93c6e9005ff3")
        private HybridTextElement textElement;

        @objid ("3e35591d-a36c-4cbc-ad32-a9010f9aec1a")
        private KeyListener keyListener;

        @objid ("02a1e894-6ef7-4ba1-8b1e-2ca69530aa37")
        public HybridCompletionDriver(final HybridTextElement textElement, CoreSession session) {
            this.searchCriteria = new ModelSearchCriteria();
            this.searcher = new ModelSearchEngine();
            this.textElement = textElement;
            connect(session);
        }

        @objid ("381e74a7-a8c6-4572-bfe4-3bed7090b326")
        private void onCtrlSpace() {
            // Reconfigure search criteria
            this.searchCriteria.reset();
            for (final MClass mc : this.textElement.getAcceptedMetaclasses()) {
                this.searchCriteria.addMetaclass(mc);
            }
            this.searchCriteria.setFilter(this.textElement.getFilter());
            
            final String expression = this.textElement.getTextControl().getText() + ".*";
            
            this.searchCriteria.setExpression(expression);
            
            final List<Element> elements = this.searcher.search(this.session, this.searchCriteria);
            
            if (elements.isEmpty()) {
                // Close the other tooltip, to avoid an exception with the dispose of the text
                if (getTooltip() != null) {
                    getTooltip().hide();
                }
            
                MessageDialog.openInformation(this.textElement.getTextControl().getShell(),
                        CoreUi.I18N.getString("TextElement.NotFoundTitle"),
                        CoreUi.I18N.getString("TextElement.NotFoundMessage"));
                this.textElement.validate(false);
            } else if (elements.size() == 1 && !this.textElement.isAcceptNullValue()) {
                this.textElement.setSelectedElement(elements.get(0));
            } else {
                // Close the other tooltip, to avoid an exception with the dispose of the text
                if (getTooltip() != null) {
                    getTooltip().hide();
                }
            
                // We have several found elements
                final PopupChooser rp = new PopupChooser(
                        this.textElement.getTextControl(),
                        elements,
                        this.textElement.isAcceptNullValue());
            
                this.textElement.setSelectedElement(rp.getChoice(getMObjectValue()));
            }
        }

        @objid ("bb5b1de0-f110-4667-b396-0df42c6cd814")
        private void onEnter() {
            final String expression = this.textElement.getTextControl().getText();
            
            if (expression.isEmpty()) {
                if (this.textElement.isAcceptStringValue()) {
                    // Set as empty string
                    this.textElement.setSelectedElement(expression);
                } else if (this.textElement.isAcceptNullValue()) {
                    this.textElement.setSelectedElement(null);
                } else {
                    this.textElement.validate(false);
                }
                
                // Exit 
                return;
            }
            
            // Reconfigure search criteria
            this.searchCriteria.reset();
            for (final MClass mc : this.textElement.getAcceptedMetaclasses()) {
                this.searchCriteria.addMetaclass(mc);
            }
            this.searchCriteria.setFilter(this.textElement.getFilter());
            this.searchCriteria.setExpression(expression);
            
            List<Element> elements = this.searcher.search(this.session, this.searchCriteria);
            
            if (elements.isEmpty()) {
                // Retry with regular expression
                this.searchCriteria.setExpression(expression + ".*");
                elements = this.searcher.search(this.session, this.searchCriteria);
            }
            
            if (elements.isEmpty()) {
                if (isAcceptStringValue()) {
                    this.textElement.setSelectedElement(expression);
                } else {
                    this.textElement.validate(false);
                }
            } else if (elements.size() == 1) {
                this.textElement.setSelectedElement(elements.get(0));
            } else {
                // Close the other tooltip, to avoid an exception with the dispose of the text
                if (getTooltip() != null) {
                    getTooltip().hide();
                }
            
                // We have several found elements
                final PopupChooser rp = new PopupChooser(
                        this.textElement.getTextControl(), 
                        elements, 
                        this.textElement.isAcceptNullValue());
                
                MObject choice = rp.getChoice(getMObjectValue());
                this.textElement.setSelectedElement(choice);
            }
            return;
        }

        /**
         * Driving the auto-completion.<br/>
         * On 'ENTER':<br/>
         * Search for an element which name is the currently entered text value
         * <ul>
         * <li>if a unique element is found, validate the entry with it</li>
         * <li>if several elements are found, open the popup chooser initialized
         * by the search results</li>
         * <li>if no element is found, open the popup initialized by a regexp
         * (current text + .*) and start the search immediately</li>
         * <li>validate the entry when the chooser popup returns</li>
         * </ul>
         * @param e
         */
        @objid ("812e67ff-9d23-4585-acf2-d6aa61636f1a")
        void onKeyPressed(KeyEvent e) {
            if (this.textElement == null) {
                return;
            }
            if (e.character == '\r') {
                onEnter();
            } else if ((e.character == ' ') && ((e.stateMask & SWT.CTRL) != 0)) {
                onCtrlSpace();
            } else if (e.character == SWT.ESC) {
                this.textElement.validate(false);
            }
        }

        @objid ("5f6960d0-4671-4ab5-8af6-857c9732de89")
        public void terminate() {
            disconnect();
            this.textElement = null;
        }

        @objid ("ebe10b75-ac2a-46d1-82cf-89d2f11ce2a3")
        private void connect(CoreSession coreSession) {
            this.session = coreSession;
            
            // Register a key listener on the wrapped text control
            this.keyListener = KeyListener.keyPressedAdapter(e -> {
                try {
                    onKeyPressed(e);
                } catch (final Exception ex) {
                    CoreUi.LOG.error(ex);
                    // Do nothing
                }
            });
            
            this.textElement.getTextControl().addKeyListener(this.keyListener);
        }

        @objid ("738a3740-f90c-4d1e-86e5-c6445c98b792")
        private void disconnect() {
            if (!this.textElement.getTextControl().isDisposed()) {
                this.textElement.getTextControl().removeKeyListener(this.keyListener);
            }
            this.keyListener = null;
            this.session = null;
        }

        @objid ("935f8b3f-b5f4-4edf-ba17-f61aa9a22088")
        private MObject getMObjectValue() {
            if (this.textElement.value instanceof MObject) {
                return (MObject)this.textElement.value;
            }
            return null;
        }

    }

    @objid ("c9c550bb-c8cd-474e-aac9-9496a0fe5d8f")
    static class HybridPickingDriver implements IPickingClient {
        @objid ("804c1699-5fb2-47a9-a12e-5cbdadf11a92")
        private IModelioPickingService pickingService;

        @objid ("c0b461d6-7f23-4522-beea-a5c7d084bc4a")
        private HybridTextElement textElement;

        @objid ("1a17c84d-ee13-4e7c-a477-f12bef097b0e")
        private FocusListener focusListener;

        @objid ("a76a6c1b-eedb-42f8-8a7e-b96418547703")
        private IPickingSession pickingSession;

        @objid ("90a37983-94d4-440f-af0a-0c663a75b880")
        private DisposeListener disposeListener;

        @objid ("63e871c1-570d-4928-b7b0-4b3358318111")
        public HybridPickingDriver(HybridTextElement textElement, IModelioPickingService pickingService) {
            this.textElement = textElement;
            this.pickingService = pickingService;
            connect();
        }

        @objid ("3735e5b4-3909-4ae6-96ed-200d9ac2f8f4")
        void startPicking() {
            if (this.pickingSession == null) {
                this.pickingSession = this.pickingService.startPicking(this);
            }
        }

        @objid ("84e6300c-6f54-4a5e-ac06-b1117b4dd885")
        void stopPicking() {
            if (this.pickingSession != null) {
                this.pickingService.stopPicking(this.pickingSession);
                this.pickingSession = null;
            }
        }

        @objid ("8e85845b-eb87-40eb-ab7f-ffec217e46c2")
        private void connect() {
            this.focusListener = new FocusListener() {
                @Override
                public void focusLost(FocusEvent e) {
                    HybridPickingDriver.this.stopPicking();
                }
            
                @Override
                public void focusGained(FocusEvent e) {
                    HybridPickingDriver.this.startPicking();
                }
            };
            this.textElement.getTextControl().addFocusListener(this.focusListener);
            
            this.disposeListener = new DisposeListener() {
            
                @Override
                public void widgetDisposed(DisposeEvent e) {
                    HybridPickingDriver.this.stopPicking();
            
                }
            };
            this.textElement.getTextControl().addDisposeListener(this.disposeListener);
        }

        @objid ("2a880c4e-1f58-4d05-a05e-29c71c65733f")
        private void disconnect() {
            if (!this.textElement.getTextControl().isDisposed()) {
                this.textElement.getTextControl().removeFocusListener(this.focusListener);
                this.textElement.getTextControl().removeDisposeListener(this.disposeListener);
            }
            this.focusListener = null;
            this.disposeListener = null;
            if (this.pickingSession != null) {
                this.pickingSession.abort();
            }
        }

        /**
         * Get rid of this picking driver: disconnect it from the text field and
         * clean up references to help garbaging.
         */
        @objid ("47235d4c-3260-4edc-b390-9ef97459431f")
        void terminate() {
            disconnect();
            this.pickingService = null;
        }

        @objid ("59318994-dcb2-44d8-962f-10496d20267c")
        @Override
        public boolean hover(MObject target) {
            // does 'target' match textElement criteria
            return (target != null) && (metaclassMatch(target))
                                                                                && ((this.textElement.getFilter() != null) ? this.textElement.getFilter().accept(target) : true);
        }

        @objid ("674b72d9-fe04-4ba1-bf42-539143cea571")
        private boolean metaclassMatch(MObject obj) {
            final Class<? extends MObject> cls = obj.getMClass().getJavaInterface();
            for (final MClass mc : this.textElement.getAcceptedMetaclasses()) {
                if (mc.getJavaInterface().isAssignableFrom(cls)) {
                    return true;
                }
            }
            return false;
        }

        @objid ("20f48b71-9a64-4589-bc90-9b630c5ea20f")
        @Override
        public boolean pick(MObject target) {
            // use target as value for TextElement
            this.textElement.setSelectedElement(target);
            return true;
        }

        @objid ("9d3a8225-810d-4201-aa5c-f0bb06f14e3c")
        @Override
        public void abort() {
            // Someone or something aborted our picking session.
            if (this.pickingSession != null) {
                this.pickingService.stopPicking(this.pickingSession);
                this.pickingSession = null;
            }
            this.textElement.validate(false);
        }

    }

    @objid ("539ff4cb-bce0-4534-b341-647788b57015")
    static class HybridDropListener extends DropTargetAdapter {
        @objid ("cfbdd3a0-eac1-466d-93f1-e997a85e1274")
        private HybridTextElement textElement;

        @objid ("c3ad0a98-8893-4ac0-8b60-ad22fc1ac11a")
        private ICoreSession session;

        @objid ("ccb62b4c-9630-426d-95bc-bbf8626a9aa3")
        public HybridDropListener(HybridTextElement textElement, ICoreSession session) {
            this.textElement = textElement;
            this.session = session;
        }

        @objid ("d9d2642f-079e-4915-ad62-6d640caddd09")
        @Override
        public void dragEnter(DropTargetEvent event) {
            super.dragEnter(event);
            List<MObject> droppedObjects = getDroppedObjects(event);
            if (!validateDroppedObjects(droppedObjects)) {
                event.detail = DND.DROP_NONE;
            }
        }

        @objid ("d99ac7d3-1a14-41d7-baba-7ca8cac4880c")
        @Override
        public void drop(DropTargetEvent event) {
            List<MObject> droppedObjects = getDroppedObjects(event);
            
            if (validateDroppedObjects(droppedObjects)) {
                this.textElement.setSelectedElement(droppedObjects.get(0));
            }
        }

        @objid ("b441e6ac-a3fd-44d4-bcf7-e257e2ba8dcb")
        @Override
        public void dropAccept(DropTargetEvent event) {
            ModelElementTransfer elementTransfer = ModelElementTransfer.getInstance();
            if (!elementTransfer.isSupportedType(event.currentDataType)) {
                event.detail = DND.DROP_NONE;
                return;
            }
            
            List<MObject> droppedObjects = getDroppedObjects(event);
            
            if (validateDroppedObjects(droppedObjects) == false) {
                event.detail = DND.DROP_NONE;
                event.feedback = DND.FEEDBACK_NONE;
                return;
            }
        }

        @objid ("bde18faa-6060-43e1-b933-19186c6aabbf")
        private boolean validateDroppedObjects(List<MObject> droppedObjects) {
            // Validate dropped objects agains't the accepted metaclasses, a
            // possible null value, and the (optional) filter
            
            List<MClass> acceptedMetaclasses = this.textElement.getAcceptedMetaclasses();
            IMObjectFilter filter = this.textElement.getFilter();
            
            // Accept only one element
            if (droppedObjects.size() != 1) {
                return false;
            }
            
            MObject obj = droppedObjects.get(0);
            if (!acceptedMetaclasses.contains(obj.getMClass()) || (filter != null && !filter.accept(obj))) {
                return false;
            }
            return true;
        }

        /**
         * Extract the list of MObject being dropped, excluding 'deleted'
         * objects.
         * 
         * @param event @return
         */
        @objid ("56de3403-dc04-4318-9135-fe39f8df5cfc")
        private List<MObject> getDroppedObjects(DropTargetEvent event) {
            ModelElementTransfer elementTransfer = ModelElementTransfer.getInstance();
            
            // Convert the transfer data to MRefs.
            MRef[] refs = (MRef[]) elementTransfer.nativeToJava(event.currentDataType);
            if (refs != null) {
                // Find model elements in the session from their refs
                List<MObject> dropedElements = new ArrayList<>();
                for (MRef mref : refs) {
                    dropedElements.add(this.session.getModel().findByRef(mref, IModel.NODELETED));
                }
                return dropedElements;
            } else {
                // On linux, the event data is not filled until the 'drop'. Try
                // getting the selection from LocalSelectionTransfer.
                return getLocalDraggedElements();
            }
        }

        /**
         * Alternative method used on Linux to extract the list of MObject being
         * dropped
         * @return
         */
        @objid ("49a3163a-584c-4cda-bbc6-719b8c6b251a")
        private List<MObject> getLocalDraggedElements() {
            List<MObject> selectedElements = new ArrayList<>();
            
            ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
            
            if (selection instanceof IStructuredSelection) {
                IStructuredSelection structuredSelection = (IStructuredSelection) selection;
                for (Iterator<?> i = structuredSelection.iterator(); i.hasNext();) {
                    Object o = i.next();
                    if (o instanceof IAdaptable) {
                        IAdaptable adapter = (IAdaptable) o;
                        MObject element = adapter.getAdapter(MObject.class);
                        if (element != null) {
                            selectedElements.add(element);
                        }
                    } else if (o instanceof MObject) {
                        selectedElements.add((MObject) o);
                    }
                }
            }
            return selectedElements;
        }

    }

}
