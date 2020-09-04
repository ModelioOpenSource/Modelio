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

package org.modelio.core.ui.swt.textelement;

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
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.PluginTransfer;
import org.modelio.api.ui.dnd.ModelElementTransfer;
import org.modelio.app.core.picking.IModelioPickingService;
import org.modelio.app.core.picking.IPickingClient;
import org.modelio.app.core.picking.IPickingSession;
import org.modelio.core.ui.plugin.CoreUi;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
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
 * TextElement is a reusable component wrapping an SWT Text widget that can be used to edit/select a single model element.<br/>
 * The following features are provided:
 * <ul>
 * <li>auto-completion by name with selection in a popup list when several elements are matching</li>
 * <li>picking of an element in the model</li>
 * <li>dropping of an element from the model</li>
 * </ul>
 * Each of these supported features has to be activated explicitly. The TextElement can be configured to accept (and propose) only certain elements on the following criteria:
 * <ul>
 * <li>accept null values</li>
 * <li>accept only certain metaclasses</i>
 * <li>accept only elements matching a given filter</li>
 * </ul>
 * Note: TextElement wraps a SWT Text because inheriting from Text is not possible. Therefore the getTextControl() method is available to reach the inner Text field, typically for layout purposes.
 */
@objid ("e70f8eef-9420-4153-b86e-1743b583273c")
public class TextElement {
    /**
     * Indicates that this TextElement should accept and propose null value
     */
    @mdl.prop
    @objid ("6fb3cc5f-ef1e-4821-91d2-55ad79ad0c47")
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

    /**
     * Accepted metaclasses
     */
    @objid ("4b03fa46-5e62-4702-9641-554c53dec312")
    private final List<MClass> metaclasses = new ArrayList<>();

    /**
     * MObject filter
     */
    @objid ("bd3e8559-a0cb-42e8-8abb-16bbc13a6b47")
    private IMObjectFilter filter;

    /**
     * The wrapped Text widget
     */
    @objid ("722565b9-052e-412e-b2f3-ce6d4f4aea3d")
    private final Text text;

    /**
     * Current value of the editor, ie either the initial value or the lastly validated one
     */
    @objid ("cf814748-e3f0-4cce-8735-8454f317661e")
    private MObject value;

    /**
     * Chosen value for the editor.<br/>
     * This element was chosen by the end-user and will become the editor value on validate(true)
     */
    @objid ("924299b0-22a4-4292-a6e5-b35dc5407d2a")
    private MObject selected;

    /**
     * The TextElement registered listeners
     */
    @objid ("2c04c138-63d3-4eac-a29f-2dcd52bf8e45")
    private final List<ITextElementSelectionListener> listeners = new ArrayList<>(1);

    /**
     * The the completion mechanism implementation.
     */
    @objid ("70af68a9-be09-495e-83cc-eb9a78e8736b")
    private CompletionDriver completionDriver = null;

    /**
     * The picking mechanism implementation
     */
    @objid ("7f74a529-fdc9-4289-9ff7-6a0f1d834050")
    private PickingDriver pickingDriver = null;

    @objid ("26395fe3-ba81-4470-845b-5884bbda2ad9")
    protected DefaultToolTip tooltip;

    /**
     * Create a TextElement. The internal Text control is created with 'parent' and 'style'. The created TextElement:
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
    @objid ("ac31aa48-ea31-407c-88c2-78c181314718")
    public TextElement(Composite parent, int style, boolean acceptNullValue) {
        this.text = createControl(parent, style);
        this.acceptNullValue = acceptNullValue;
    }

    /**
     * Set a filter that will be used to accept (and propose) elements.
     * @param filter
     */
    @objid ("6a997806-b01c-4b3c-b421-fc2737566547")
    public void setFilter(IMObjectFilter filter) {
        this.filter = filter;
    }

    /**
     * Returns the current filter
     * @return
     */
    @objid ("9f8b07b7-1fe4-42ec-9148-4840ef8c9039")
    public IMObjectFilter getFilter() {
        return this.filter;
    }

/*
     * Get the list of the accepted metaclasses for the elements. Add metaclasses to this list to complete it.
     */
    @objid ("40e35022-8e04-4e24-8070-55645c40638b")
    public List<MClass> getAcceptedMetaclasses() {
        return this.metaclasses;
    }

    /**
     * Activate completion.
     * @param session
     */
    @objid ("5ac28252-ae38-4ab5-a31f-42f731aecc58")
    public void activateCompletion(ICoreSession session) {
        if (session != null) {
            this.completionDriver = new CompletionDriver(this, (CoreSession) session, this.tooltip);
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
    @objid ("8cbef902-e2aa-453d-a92e-df6203e2c565")
    public void activatePicking(IModelioPickingService pickingService) {
        if (pickingService != null) {
            this.pickingDriver = new PickingDriver(this, pickingService);
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
    @objid ("8677f14c-9a83-4dfb-a068-63ae1f05b796")
    public void activateDragAndDrop(ICoreSession session) {
        DropListener dropListener = new DropListener(this, session);
        int operations = DND.DROP_MOVE | DND.DROP_COPY;
        Transfer[] types = new Transfer[] { ModelElementTransfer.getInstance(), PluginTransfer.getInstance() };
        DropTarget target = new DropTarget(this.text, operations);
        target.setTransfer(types);
        target.addDropListener(dropListener);
    }

    @objid ("2a8ae7f7-6ac5-4c3e-bd18-9e112a3e5dc1")
    public void addListener(final ITextElementSelectionListener listener) {
        this.listeners.add(listener);
    }

    @objid ("7a337993-4c4e-498b-951e-ee559d7a38ef")
    public void setValue(MObject value) {
        this.value = value;
        this.text.setText((value != null) ? value.getName() : "");
    }

    @objid ("14b7ac79-01f0-496d-942c-eb508f6eb16e")
    public MObject getValue() {
        return this.value;
    }

    /**
     * Returns the internal text control. Should be used only for setting layout data.
     * @return
     */
    @objid ("0066a4b1-e368-4ae0-80b4-ea7b53b2ec64")
    public Text getTextControl() {
        return this.text;
    }

    @objid ("e410f900-cf06-4a82-8572-e0879126d1f4")
    public void removeListener(final ITextElementSelectionListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Create and configure the wrapped text control
     * @param parent
     * @param style
     * 
     * @return the configured Text control
     */
    @objid ("410d2704-bee0-49ea-946f-187b050fcfe1")
    private Text createControl(Composite parent, int style) {
        final Text wrappedText = new Text(parent, style);
        wrappedText.addPaintListener(new TextElementPaintListener());
        
        this.tooltip = new DefaultToolTip(wrappedText) {
            @Override
            protected String getText(Event event) {
                return TextElement.this.getToolTipText();
            }
        };
        return wrappedText;
    }

    @objid ("bfbd7a6b-f3c9-4af8-9db8-244ff28ce279")
    private void fireSelectedElementChanged(final MObject oldElement, final MObject newElement) {
        for (final ITextElementSelectionListener listener : this.listeners) {
            listener.selectedElementChanged(oldElement, newElement);
        }
    }

    /**
     * @return
     */
    @objid ("3abf6d3c-598d-4fd4-9df6-0c0184c0e5e4")
    private String getToolTipText() {
        final StringBuffer helpTooltip = new StringBuffer();
        
        if (this.metaclasses.size() > 0) {
            helpTooltip.append(CoreUi.I18N.getString("TextElement.AcceptedTypes"));
        
            helpTooltip.append("\n");
            for (final MClass clazz : this.metaclasses) {
                helpTooltip.append("    ");
                helpTooltip.append(clazz.getName());
                helpTooltip.append("\n");
            }
        
            helpTooltip.append("\n");
        }
        
        if (this.completionDriver != null) {
            helpTooltip.append(CoreUi.I18N.getString("TextElement.CompletionUsage"));
        }
        return helpTooltip.toString();
    }

    @objid ("7995de33-581d-4d81-990d-204e08813663")
    private void setSelectedElement(MObject element) {
        this.selected = element;
        
        // If the element is not null, display its name in the text field
        if (element != null) {
            String textString = element.getName();
            if (element.getCompositionOwner() != null) {
                textString = textString + "  (from " + element.getCompositionOwner().getName() + ")";
            }
            // update text and data
            if (!this.text.isDisposed()) {
                this.text.setText(textString);
            }
        
        }
        validate(true);
    }

    @objid ("b3760393-31e1-4d15-979b-850072a78afa")
    private void validate(boolean save) {
        if (save) {
            // Update the data model from the content of the text field.
            final MObject oldElement = this.value;
            this.value = this.selected;
            fireSelectedElementChanged(oldElement, this.value);
        }
        
        // Close the tooltip, to avoid an exception with the dispose of the text
        if (this.tooltip != null) {
            this.tooltip.hide();
            this.tooltip = null;
        }
    }

    /**
     * Wrapped text decorator. Paints a blue border around the text along with an 'field assist' icon.
     * 
     * @author phv
     */
    @objid ("1c74315a-50f5-4586-9fd2-8cf6f11c48b3")
    private static class TextElementPaintListener implements PaintListener {
        @objid ("269a0ffe-f20e-4e37-97d4-75a61a7feb92")
        @Override
        public void paintControl(PaintEvent e) {
            final GC gc = e.gc;
            final Rectangle clip = new Rectangle(e.x, e.y, e.width, e.height);
            
            final Rectangle imageRect = UIImages.ASSIST.getBounds();
            gc.drawImage(UIImages.ASSIST, clip.x + clip.width - imageRect.width, clip.y);
        }

        @objid ("cb8668ea-e904-44d4-919c-175e313a18d3")
        public TextElementPaintListener() {
            // nothing to do
        }

    }

    /**
     * Completion driver. Search model elements matching the current text and the configured completion criteria and propose them to user's choice.
     * 
     * @author phv
     */
    @objid ("5906d77a-b077-4b9e-a4b8-a4b9036c419b")
    static class CompletionDriver {
        @objid ("b4abca67-1fbc-40e0-abb3-eb49b3e1dc82")
        private final ModelSearchCriteria searchCriteria;

        @objid ("8bb5362e-4837-44af-ab3e-67abae244139")
        private final ModelSearchEngine searcher;

        @objid ("1b4d896f-f22a-4fec-b96c-e3835ddf33e2")
        private CoreSession session;

        @objid ("0a11e616-5465-40cc-b1b8-3203a8339391")
        private Text text;

        @objid ("74164e24-0cdf-47ab-b099-9618f61e66fa")
        private TextElement textElement;

        @objid ("447021c5-8b29-46b4-807c-9b87c9efe4cb")
        private KeyListener keyListener;

        @objid ("3c5081ca-61b6-4ea3-9d5c-ab02246cbe4e")
        private DefaultToolTip tooltip;

        @objid ("65285751-2e8e-44fb-b0cf-20276c910abe")
        public CompletionDriver(final TextElement textElement, CoreSession session, DefaultToolTip tooltip) {
            this.searchCriteria = new ModelSearchCriteria();
            this.searcher = new ModelSearchEngine();
            this.textElement = textElement;
            this.text = textElement.getTextControl();
            this.tooltip = tooltip;
            connect(session);
        }

        @objid ("dbc534c3-b241-43ce-9954-62ab692ed8ec")
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
                MessageDialog.openInformation(this.text.getShell(), CoreUi.I18N.getString("TextElement.NotFoundTitle"),
                        CoreUi.I18N.getString("TextElement.NotFoundMessage"));
                this.textElement.validate(false);
            } else if (elements.size() == 1 && !this.textElement.isAcceptNullValue()) {
                this.textElement.setSelectedElement(elements.get(0));
            } else {
                // Close the other tooltip, to avoid an exception with the dispose of the text
                if (this.tooltip != null) {
                    this.tooltip.hide();
                }
            
                // We have several found elements
                final PopupChooser rp = new PopupChooser(this.textElement.getTextControl(), elements,
                        this.textElement.isAcceptNullValue());
                final ModelElement selected = (ModelElement) rp.getChoice(this.textElement.value);
            
                this.textElement.setSelectedElement(selected);
            }
        }

        @objid ("2eb32355-fa64-4d05-9c87-bd30b6f9ae7d")
        private void onEnter() {
            // Reconfigure search criteria
            this.searchCriteria.reset();
            for (final MClass mc : this.textElement.getAcceptedMetaclasses()) {
                this.searchCriteria.addMetaclass(mc);
            }
            this.searchCriteria.setFilter(this.textElement.getFilter());
            
            final String expression = this.text.getText();
            this.searchCriteria.setExpression(expression);
            
            List<Element> elements = this.searcher.search(this.session, this.searchCriteria);
            
            if (elements.isEmpty()) {
                // Retry with regular expression
                this.searchCriteria.setExpression(expression + ".*");
                elements = this.searcher.search(this.session, this.searchCriteria);
            
                if (elements.isEmpty()) {
                    this.textElement.validate(false);
                } else if (elements.size() == 1) {
                    this.textElement.setSelectedElement(elements.get(0));
                } else {
                    // Close the other tooltip, to avoid an exception with the dispose of the text
                    if (this.tooltip != null) {
                        this.tooltip.hide();
                    }
            
                    // We have several found elements
                    final PopupChooser rp = new PopupChooser(this.text, elements, this.textElement.isAcceptNullValue());
                    final ModelElement selected = (ModelElement) rp.getChoice(this.textElement.value);
                    this.textElement.setSelectedElement(selected);
                }
            } else if (elements.size() == 1) {
                this.textElement.setSelectedElement(elements.get(0));
            } else {
                // Close the other tooltip, to avoid an exception with the dispose of the text
                if (this.tooltip != null) {
                    this.tooltip.hide();
                }
            
                // We have several found elements
                final PopupChooser rp = new PopupChooser(this.text, elements, this.textElement.isAcceptNullValue());
                final ModelElement selected = (ModelElement) rp.getChoice(this.textElement.value);
                this.textElement.setSelectedElement(selected);
            }
            return;
        }

        /**
         * Driving the auto-completion.<br/>
         * On 'ENTER':<br/>
         * Search for an element which name is the currently entered text value
         * <ul>
         * <li>if a unique element is found, validate the entry with it</li>
         * <li>if several elements are found, open the popup chooser initialized by the search results</li>
         * <li>if no element is found, open the popup initialized by a regexp (current text + .*) and start the search immediately</li>
         * <li>validate the entry when the chooser popup returns</li>
         * </ul>
         * @param e
         */
        @objid ("37fdaa3a-31c8-4715-b6d4-d246e4f9e404")
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

        @objid ("98079e41-fc99-4beb-b553-37d756175074")
        public void terminate() {
            disconnect();
            this.textElement = null;
            this.text = null;
        }

        @objid ("da2933f9-d5ba-405c-9d80-21834e9ab725")
        private void connect(CoreSession coreSession) {
            this.session = coreSession;
            
            // Register a key listener on the wrapped text control
            this.keyListener = new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    try {
                        onKeyPressed(e);
                    } catch (final Exception ex) {
                        ex.printStackTrace();
                        // Do nothing
                    }
                }
            };
            
            this.textElement.getTextControl().addKeyListener(this.keyListener);
        }

        @objid ("556e862f-06ce-48f0-ab8f-8a476d1717b1")
        private void disconnect() {
            if (!this.text.isDisposed()) {
                this.text.removeKeyListener(this.keyListener);
            }
            this.keyListener = null;
            this.session = null;
        }

    }

    @objid ("2ba901bc-9571-409a-96f6-26ab0493324b")
    static class PickingDriver implements IPickingClient {
        @objid ("71c794ed-61fc-4708-8b87-654567ee5875")
        private IModelioPickingService pickingService;

        @objid ("d9d6dc91-3877-44af-ba0f-e694f5e116b8")
        private TextElement textElement;

        @objid ("5569468e-21d4-4d53-93b5-896dc2b65731")
        private FocusListener focusListener;

        @objid ("cc247753-f1b1-4cc4-ad6a-49f7ff75ab39")
        private IPickingSession pickingSession;

        @objid ("8a3c679d-ef77-4fe5-b977-ec3ecca387e0")
        private DisposeListener disposeListener;

        @objid ("91485e7d-16ee-4ddf-8def-591e3d49afda")
        public PickingDriver(TextElement textElement, IModelioPickingService pickingService) {
            this.textElement = textElement;
            this.pickingService = pickingService;
            connect();
        }

        @objid ("7176fe97-535e-4630-8d57-8a34d065a50d")
        void startPicking() {
            if (this.pickingSession == null) {
                this.pickingSession = this.pickingService.startPicking(this);
            }
        }

        @objid ("6e213995-21df-4ecb-a400-9ef6a38d8448")
        void stopPicking() {
            if (this.pickingSession != null) {
                this.pickingService.stopPicking(this.pickingSession);
                this.pickingSession = null;
            }
        }

        @objid ("003cef22-e939-4463-b9b2-559f6cabb9f5")
        private void connect() {
            this.focusListener = new FocusListener() {
                @Override
                public void focusLost(FocusEvent e) {
                    PickingDriver.this.stopPicking();
                }
            
                @Override
                public void focusGained(FocusEvent e) {
                    PickingDriver.this.startPicking();
                }
            };
            this.textElement.getTextControl().addFocusListener(this.focusListener);
            
            this.disposeListener = new DisposeListener() {
            
                @Override
                public void widgetDisposed(DisposeEvent e) {
                    PickingDriver.this.stopPicking();
            
                }
            };
            this.textElement.getTextControl().addDisposeListener(this.disposeListener);
        }

        @objid ("4976289f-e257-482e-9df2-1e98abe0b7a6")
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
         * Get rid of this picking driver: disconnect it from the text field and clean up references to help garbaging.
         */
        @objid ("63c74bf7-7712-4168-a699-0e2f29a38d67")
        void terminate() {
            disconnect();
            this.pickingService = null;
        }

        @objid ("1228e614-8211-401d-92a6-2c4e83ae0d6a")
        @Override
        public boolean hover(MObject target) {
            // does 'target' match textElement criteria
            return (target != null) && (metaclassMatch(target))
                                && ((this.textElement.filter != null) ? this.textElement.filter.accept(target) : true);
        }

        @objid ("25ac46b7-a742-41f2-a67b-2f57b1e2f149")
        private boolean metaclassMatch(MObject obj) {
            final Class<? extends MObject> cls = obj.getMClass().getJavaInterface();
            for (final MClass mc : this.textElement.metaclasses) {
                if (mc.getJavaInterface().isAssignableFrom(cls)) {
                    return true;
                }
            }
            return false;
        }

        @objid ("dfb28ba8-5211-423b-a1a3-74c15c2690c7")
        @Override
        public boolean pick(MObject target) {
            // use target as value for TextElement
            this.textElement.setSelectedElement(target);
            return true;
        }

        @objid ("f2733d88-6fc2-41b5-b5dd-667d04f1a83d")
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

    @objid ("543c1cad-1765-4a84-b33e-0b803b1ee36e")
    static class DropListener extends DropTargetAdapter {
        @objid ("13fb3a1e-678f-4cef-a45f-e98d11cad0ba")
        private TextElement textElement;

        @objid ("cadefaaf-44be-4e35-b83e-24c188700cbb")
        private ICoreSession session;

        @objid ("3bd1ce00-f717-4424-ae0e-74cc65e5bb53")
        public DropListener(TextElement textElement, ICoreSession session) {
            this.textElement = textElement;
            this.session = session;
        }

        @objid ("8ab7475b-970b-4489-990d-d9b4fd9a2dfd")
        @Override
        public void dragEnter(DropTargetEvent event) {
            super.dragEnter(event);
            List<MObject> droppedObjects = getDroppedObjects(event);
            if (!validateDroppedObjects(droppedObjects)) {
                event.detail = DND.DROP_NONE;
            }
        }

        @objid ("bc66ec5f-a3fd-4b5a-9dbc-12db769e3d10")
        @Override
        public void drop(DropTargetEvent event) {
            List<MObject> droppedObjects = getDroppedObjects(event);
            
            if (validateDroppedObjects(droppedObjects)) {
                this.textElement.setSelectedElement(droppedObjects.get(0));
            }
        }

        @objid ("f4e12f1a-e280-41bd-9054-71d17dfe648f")
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

        @objid ("f43822ed-895f-402c-9012-0f456f9620e2")
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
         * Extract the list of MObject being dropped, excluding 'deleted' objects.
         * 
         * @param event @return
         */
        @objid ("73b024de-35de-47b2-9d2c-a62a32f7ff21")
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
         * Alternative method used on Linux to extract the list of MObject being dropped
         * @return
         */
        @objid ("7f01ebe3-765b-4c83-b065-70db88276923")
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
