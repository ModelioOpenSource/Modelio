/* 
 * Copyright 2013-2020 Modeliosoft
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
package org.modelio.platform.model.ui.swt.selectmetaclass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalListener;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.modelio.platform.model.ui.plugin.CoreUi;
import org.modelio.platform.model.ui.swt.images.MetamodelImageService;
import org.modelio.platform.ui.UIImages;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * SelectMetaclass is a reusable component wrapping an SWT Text widget that can
 * be used to select a single metaclass.<br/>
 * The proposal menu is popped whenever one of the following conditions is
 * fulfilled:
 * <ul>
 * <li>the user enters any upper case letter character (as metaclass names
 * always starts with a Capital letter)</li>
 * <li>the user enters CTRL+SPACE which is the standard completion activation
 * sequence</li>
 * </ul>
 * The metaclasses proposed for completion are those defined in the metamodel
 * filtered by IMetaclassSelectorFilter if such a filter is set.
 * Note: SelectMetaclass wraps a SWT Text because inheriting from Text is not
 * possible. Therefore the getTextControl() method is available to reach the
 * inner Text field, typically for layout purposes.
 */
@objid ("7822a1ad-c148-445d-88ed-e143f982a3c5")
public class MetaclassSelector {
    @objid ("39748845-4a64-4592-aa89-514a9e5b99ca")
    private static final char[] AUTO_ACTIVATION_CHARS = "ABCDEFGHIJKLMNOPQRSTUVW".toCharArray();

    @objid ("d09e8cc1-d770-4ba5-90f5-7012e7394c8e")
    private final List<IMetaclassSelectorListener> listeners = new ArrayList<>(1);

    /**
     * The wrapped Text widget
     */
    @objid ("e4964504-be5d-4526-9c46-c5f9952e343a")
    private final Text text;

    
    @mdl.prop
    @objid ("92660e38-1c61-4ab5-a65b-b92b11819618")
    public IMetaclassSelectorFilter metaclassFilter;

    @mdl.propgetter
    public IMetaclassSelectorFilter getMetaclassFilter() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.metaclassFilter;
    }

    @mdl.propsetter
    public void setMetaclassFilter(IMetaclassSelectorFilter value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.metaclassFilter = value;
    }

    @objid ("f19df62c-77a6-42e8-9ff5-2b56125c1fe2")
    private MMetamodel metamodel;

    @objid ("1768c632-1189-4bf3-a171-f30f159d1b13")
    public  MetaclassSelector(Composite parent, int style, MMetamodel metamodel) {
        this(parent, style, metamodel, null);
    }

    @objid ("4473cd28-07c5-44dd-93c9-e4df3526b9fc")
    public  MetaclassSelector(Composite parent, int style, MMetamodel metamodel, IMetaclassSelectorFilter filter) {
        this.text = createControl(parent, style);
        this.metaclassFilter = filter;
        this.metamodel = metamodel;
        
    }

    @objid ("b01e5868-3de8-4767-af72-8c8ba9eccb59")
    public synchronized void addListener(final IMetaclassSelectorListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Returns the internal text control. Should be used only for setting layout
     * data.
     */
    @objid ("df0f69b4-31c4-4f39-bc8a-b7dd9292095a")
    public Text getControl() {
        return this.text;
    }

    @objid ("f4a21913-2173-4dcb-a21d-d1b94a1dae89")
    public MClass getSelected() {
        return ((MClass) this.text.getData());
        // return this.metamodel.getMClass(MetaclassSelector.this.text.getData());
    }

    @objid ("bf8a7608-a95f-4185-8c38-60729d5de25f")
    public synchronized void removeListener(final IMetaclassSelectorListener listener) {
        this.listeners.remove(listener);
    }

    @objid ("d6d998b3-d9a3-4b5e-b70b-78f70acefcc5")
    public void setSelected(MClass mClass) {
        if (mClass != null) {
            this.text.setData(mClass);
            this.text.setText(mClass.getName());
        } else {
            this.text.setData(null);
            this.text.setText("");
        }
        
    }

    @objid ("5660494b-17d2-4f1a-b525-9e74de48ade9")
    private Text createControl(Composite parent, int style) {
        final Text wrappedText = new Text(parent, style);
        
        // create the decoration for the text component
        final ControlDecoration deco = new ControlDecoration(wrappedText, SWT.TOP | SWT.RIGHT);
        
        // set description and image
        deco.setDescriptionText(CoreUi.I18N.getString("MetaclassSelector.assist.tooltip"));
        deco.setImage(UIImages.ASSIST);
        
        // always show decoration
        deco.setShowOnlyOnFocus(true);
        
        try {
            KeyStroke k = KeyStroke.getInstance("CTRL+SPACE");
            ContentProposalAdapter adapter = new ContentProposalAdapter(wrappedText, new TextContentAdapter(),
                    new SelectMetaclassContentProposalProvider(this), k, MetaclassSelector.AUTO_ACTIVATION_CHARS);
        
            adapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
        
            adapter.setLabelProvider(new LabelProvider() {
                @Override
                public Image getImage(Object element) {
                    MClass mclass = ((MetaclassProposal) element).getMClass();
                    return MetamodelImageService.getIcon(mclass);
                }
        
                @Override
                public String getText(Object element) {
                    MetaclassProposal p = (MetaclassProposal) element;
                    return p.getLabel() + " (" + p.getMClass().getOrigin().getName() + ")";
                }
            });
        
            adapter.addContentProposalListener(new IContentProposalListener() {
                @Override
                public void proposalAccepted(IContentProposal proposal) {
                    MetaclassSelector.this.text.setData(((MetaclassProposal) proposal).getMClass());
                    fireSelection();
                    MetaclassSelector.this.text.traverse(SWT.TRAVERSE_TAB_NEXT);
                }
            });
        
            wrappedText.addFocusListener(new FocusListener() {
                @Override
                public void focusLost(FocusEvent e) {
                    if (!adapter.isProposalPopupOpen()) {
                        showMetaclassValidity();
                        fireSelection();
                    }
                }
        
                @Override
                public void focusGained(FocusEvent e) {
                    // Nothing to do
                }
            });
            wrappedText.addSelectionListener(new SelectionListener() {
        
                @Override
                public void widgetSelected(SelectionEvent e) {
                    // Nothing to do
        
                }
        
                @Override
                public void widgetDefaultSelected(SelectionEvent e) {
                    // Called on ENTER
                    showMetaclassValidity();
                    fireSelection();
                }
            });
        
            wrappedText.addModifyListener(new ModifyListener() {
                @Override
                public void modifyText(ModifyEvent e) {
                    showMetaclassValidity();
                }
            });
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return wrappedText;
    }

    @objid ("94b15067-b323-4fdc-9291-ad1bf9b5a224")
    private void fireSelection() {
        MClass mClass = getSelected();
        for (final IMetaclassSelectorListener listener : this.listeners) {
            listener.selectMetaclass(mClass);
        }
        
    }

    @objid ("816c8d8e-37b4-4698-8113-f5b2d2c30205")
    private void showMetaclassValidity() {
        String metaclassName = MetaclassSelector.this.text.getText();
        final MClass mClass = this.metamodel.getMClass(metaclassName);
        if (mClass != null) {
            MetaclassSelector.this.text.setForeground(MetaclassSelector.this.text.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
        } else {
            MetaclassSelector.this.text.setForeground(MetaclassSelector.this.text.getDisplay().getSystemColor(SWT.COLOR_RED));
        }
        
        // We have to check the data's name before replacing it, to handle metaclasses having the same name
        Object currentData = this.text.getData();
        if (currentData == null || !((MClass) currentData).getName().equals(metaclassName)) {
            this.text.setData(mClass);
        }
        
    }

    @objid ("14efd48a-82a1-4d99-8c1a-ae0e3aafcec8")
    private static class MetaclassProposal implements IContentProposal {
        @objid ("859cdb68-2969-455c-a8b0-1dc87bb2bcc7")
        private MClass mClass;

        @objid ("5208c5f6-6558-48ab-83bf-e744d3332084")
        public  MetaclassProposal(MClass mClass) {
            this.mClass = mClass;
        }

        @objid ("7f978a93-1a47-4fca-b156-a9bef9be525f")
        public MClass getMClass() {
            return this.mClass;
        }

        @objid ("7cf8e907-9563-4b51-9737-6156c9961a91")
        @Override
        public String getContent() {
            return this.mClass.getName();
        }

        @objid ("bce6cf5a-81aa-4fb6-b99d-9f351bb937f3")
        @Override
        public int getCursorPosition() {
            return this.mClass.getName().length();
        }

        @objid ("dd158b58-bbc4-4a2d-8d7b-df1a71aebf8a")
        @Override
        public String getLabel() {
            return this.mClass.getName();
        }

        @objid ("eaf8e3fb-bd2a-4b33-bf79-fa46250723a4")
        @Override
        public String getDescription() {
            return this.mClass.getName();
        }

    }

    @objid ("41709500-b7c3-4ca2-9fd3-77390858583b")
    private static class SelectMetaclassContentProposalProvider implements IContentProposalProvider {
        @objid ("cead09dd-ef62-4274-95e7-418cb17ae340")
        private List<MClass> metaclasses;

        @objid ("f59bfbeb-e4a6-4a16-8c61-65b1d921c284")
        private MetaclassSelector metaclassSelector;

        @objid ("403e97c5-302a-407b-b230-ada567f2d2ff")
        @Override
        public IContentProposal[] getProposals(String contents, int position) {
            if (this.metaclasses == null) {
                initMetaclasses();
            }
            
            final IMetaclassSelectorFilter filter = this.metaclassSelector.getMetaclassFilter();
            
            List<IContentProposal> list = new ArrayList<>();
            for (MClass mc : this.metaclasses) {
                if (filter == null || filter.accept(mc)) {
                    if (mc.getName().length() >= contents.length()
                            && mc.getName().substring(0, contents.length()).equalsIgnoreCase(contents)) {
                        list.add(new MetaclassProposal(mc));
                    }
                }
            }
            return list.toArray(new IContentProposal[list.size()]);
        }

        @objid ("0e10c335-1f15-417a-8477-68737004cc8a")
        private void initMetaclasses() {
            this.metaclasses = new ArrayList<>(this.metaclassSelector.metamodel.getRegisteredMClasses());
            Collections.sort(this.metaclasses, new Comparator<MClass>() {
                @Override
                public int compare(MClass o1, MClass o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
            
        }

        @objid ("5c328c18-63a1-4c8b-9b2c-df5dd7f07f6d")
        public  SelectMetaclassContentProposalProvider(MetaclassSelector metaclassSelector) {
            this.metaclassSelector = metaclassSelector;
        }

    }

}
