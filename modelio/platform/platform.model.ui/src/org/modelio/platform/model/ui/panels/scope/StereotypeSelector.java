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
package org.modelio.platform.model.ui.panels.scope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.platform.mda.infra.MdaResources;
import org.modelio.platform.model.ui.plugin.CoreUi;
import org.modelio.platform.ui.UIImages;

/**
 * Reusable component wrapping an SWT Text widget that can be used to select a single metaclass.
 * <p>
 * Note: {@link StereotypeSelector} wraps a SWT {@link Text} because inheriting from {@link Text} is not possible. Therefore the {@link #getControl()} method is available to reach the inner Text field, typically for layout purposes.
 */
@objid ("f5171418-97c3-44df-a7fa-53450cf96c3c")
public class StereotypeSelector {
    @objid ("ec382cf2-eee0-4b15-9237-4ad9dd73be2a")
    private SelectStereotypeContentProposalProvider contentProvider;

    //private final IImageService imageSvc;
    @objid ("19a32677-a006-4b15-8dab-8478dd580aad")
    private final List<IStereotypeSelectorListener> listeners = new ArrayList<>(1);

    @objid ("995b5396-a714-4ed8-b2e5-6cf3d7c0ef63")
    private Predicate<Stereotype> stereotypeFilter;

    @objid ("9eb4c008-7593-48f9-9054-73678651a06c")
    private final Map<String, Stereotype> stereotypesMap;

    /**
     * The wrapped Text widget
     */
    @objid ("2bb0b8a6-d20c-44b7-83ea-343a813d9cb3")
    private final Text text;

    // protected final IMetamodelExtensions i18nsupport;
    @objid ("8bf0a344-8852-4b25-915e-b7c4e3803f05")
    public  StereotypeSelector(Composite parent, int style, int decoPos) {
        this(parent, style, decoPos, null);
    }

    @objid ("07c6c647-175d-4d89-93d3-c32a63458eef")
    public  StereotypeSelector(Composite parent, int style, int decoPos, Predicate<Stereotype> filter) {
        //        this.imageSvc = context.getModelioServices().getImageService();
        //        this.i18nsupport = context.getModelingSession().getMetamodelExtensions();
                this.text = createControl(parent, style, decoPos);
                this.stereotypeFilter = filter;
                this.stereotypesMap = new HashMap<>();
        
    }

    @objid ("bedf234c-20cf-4f5c-bcd5-9947af4b5960")
    public synchronized void addListener(final IStereotypeSelectorListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Returns the internal text control.
     * <p>
     * Should be used only for setting layout data.
     * @return the internal text control.
     */
    @objid ("0df560ca-de4b-4dfb-b651-22a1f2b494d0")
    public Text getControl() {
        return this.text;
    }

    @objid ("0379b511-8c7b-4214-858f-eeb54e2e222a")
    public Stereotype getSelected() {
        return this.stereotypesMap.get(StereotypeSelector.this.text.getText());
    }

    @objid ("9a419249-75ac-48fd-b535-8d2c3192b960")
    public synchronized void removeListener(final IStereotypeSelectorListener listener) {
        this.listeners.remove(listener);
    }

    @objid ("800c37ac-9b42-4fd6-90f5-bdd785804c93")
    public void setSelected(Stereotype ste) {
        if (ste != null) {
            this.text.setText(ste.getName());
        } else {
            this.text.setText("");
        }
        
    }

    @objid ("4b1e374e-3227-424f-8c85-8de5d07ceede")
    public void setStereotypes(Collection<Stereotype> avStereotypes) {
        final Predicate<Stereotype> filter = getStereotypeFilter();
        
        for (Stereotype ster : avStereotypes) {
            if (!this.stereotypesMap.containsKey(ster.getName())
                    && (filter == null || filter.test(ster))) {
                this.stereotypesMap.put(ster.getName(), ster);
            }
        }
        
        this.contentProvider.setStereotypes(avStereotypes);
        showStereotypeValidity();
        
    }

    @objid ("f5a29c65-c6cb-4d1b-96ac-a6c40977247f")
    protected Predicate<Stereotype> getStereotypeFilter() {
        return this.stereotypeFilter;
    }

    @objid ("2bf33bb7-f779-4fd5-9c2a-b57b612fc809")
    private Text createControl(Composite parent, int style, int decoPos) {
        final Text wrappedText = new Text(parent, style);
        
        // create the decoration for the text component
        final ControlDecoration deco = new ControlDecoration(wrappedText, decoPos);
        
        // set description and image
        deco.setDescriptionText(CoreUi.I18N.getString("StereotypeSelector.assist.tooltip"));
        deco.setImage(UIImages.ASSIST);
        
        // always show decoration
        deco.setShowOnlyOnFocus(true);
        
        this.contentProvider = new SelectStereotypeContentProposalProvider(this);
        
        ContentProposalAdapter adapter = new ContentProposalAdapter(
                wrappedText,
                new TextContentAdapter(),
                this.contentProvider,
                KeyStroke.getInstance(SWT.MOD1, SWT.SPACE),
                "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray());
        
        adapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
        
        adapter.setLabelProvider(new LabelProvider() {
            @Override
            public String getText(Object element) {
                Stereotype p = ((StereotypeProposal) element).getStereotypeData();
                return p.getName();
            }
        
            @Override
            public Image getImage(Object element) {
                Stereotype ster = ((StereotypeProposal) element).getStereotypeData();
        
                // FIXME get the icon from the module owning the stereotype
                Image icon = MdaResources.getIcon(ster);
                if (icon == null) {
                    return  UIImages.PLACEHOLDER;
                }
                return icon;
            }
        });
        
        adapter.addContentProposalListener(proposal -> {
            fireSelection();
            StereotypeSelector.this.text.traverse(SWT.TRAVERSE_TAB_NEXT);
        });
        
        wrappedText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (!adapter.isProposalPopupOpen()) {
                    showStereotypeValidity();
                    fireSelection();
                }
            }
        
        });
        wrappedText.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // Called on ENTER
                showStereotypeValidity();
                fireSelection();
            }
        });
        
        wrappedText.addModifyListener(e -> showStereotypeValidity());
        return wrappedText;
    }

    @objid ("afce6c1e-de8f-4288-97e9-cccd6c73445b")
    private void fireSelection() {
        Stereotype stereotype = getSelected();
        for (final IStereotypeSelectorListener listener : this.listeners) {
            listener.selectStereotype(stereotype);
        }
        
    }

    @objid ("b6ecb935-f643-4769-b6d5-8f93716c3b2f")
    private void showStereotypeValidity() {
        final Stereotype stereotype = this.stereotypesMap.get(this.text.getText());
        if (stereotype != null) {
            this.text.setForeground(this.text.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
        } else {
            this.text.setForeground(this.text.getDisplay().getSystemColor(SWT.COLOR_RED));
        }
        
    }

    @objid ("04d182ca-2acb-4e12-b6cf-bd2ce165a42f")
    public void setStereotypeFilter(Predicate<Stereotype> stereotypeFilter) {
        this.stereotypeFilter = stereotypeFilter;
    }

    @objid ("de80ff55-b479-48f6-b38a-2d80ff88f822")
    public interface IStereotypeSelectorListener {
        @objid ("a61cb64d-cb98-42d3-b10e-9a97b055052e")
        void selectStereotype(Stereotype stereotype);
}
    

    @objid ("e1194612-8dc2-4484-bd29-d3bfe5348ec2")
    private static class SelectStereotypeContentProposalProvider implements IContentProposalProvider {
        @objid ("fbe11b71-e83a-4956-ad66-b3b5db378121")
        private Collection<Stereotype> stereotypes;

        @objid ("c420216b-77eb-4935-9e84-90d63323eee7")
        private final StereotypeSelector stereotypeSelector;

        @objid ("ac0b46fc-c435-4a01-8d45-b7de1397542f")
        @Override
        public IContentProposal[] getProposals(String contents, int position) {
            Predicate<Stereotype> filter = this.stereotypeSelector.getStereotypeFilter();
            IContentProposal[] ret = this.stereotypes
                    .stream()
                    .filter(mc -> (filter == null || filter.test(mc))
                            && mc.getName().regionMatches(true, 0, contents, 0, contents.length()))
                    .map(s -> new StereotypeProposal(s))
                    .toArray(IContentProposal[]::new);
            return ret;
        }

        @objid ("699aa1f7-6484-4643-8a14-f712eaf4a3d1")
        public  SelectStereotypeContentProposalProvider(StereotypeSelector stereotypeSelector) {
            this.stereotypeSelector = stereotypeSelector;
            this.stereotypes = Collections.emptyList();
            
        }

        @objid ("9360dcca-877c-4ade-9c3d-9b1bf3b7c7f1")
        public void setStereotypes(Collection<Stereotype> avStereotypes) {
            this.stereotypes = avStereotypes;
        }

    }

    @objid ("855becbe-2216-488f-8676-c5323ff05997")
    private static class StereotypeProposal implements IContentProposal {
        @objid ("9bd88b4a-01ab-44e5-8b01-9e900acf4fd5")
        private final Stereotype stereotype;

        @objid ("fed7e1e2-841a-42ce-bddd-a4f4ae212244")
        public  StereotypeProposal(Stereotype stereotype) {
            this.stereotype = stereotype;
        }

        @objid ("67ba4123-e9d1-482a-8eb9-8a1715176dd1")
        public Stereotype getStereotypeData() {
            return this.stereotype;
        }

        @objid ("355a2f45-8211-46a7-8f7c-e951de92e5ce")
        @Override
        public String getContent() {
            return this.stereotype.getName();
        }

        @objid ("0dd6e847-12c1-4336-86c8-6cb3655e0843")
        @Override
        public int getCursorPosition() {
            return this.stereotype.getName().length();
        }

        @objid ("74740914-cf81-42ca-b0e7-85924d4359fa")
        @Override
        public String getLabel() {
            String label = null; // FIXME ask module owner of the stereotype to provide a label ? this.i18nSupport.getLabel(this.stereotype);
            String name = this.stereotype.getName();
            if (isWrong(label, name)) {
                return name;
            }
            return label + " (" + name + ")";
        }

        @objid ("322a808e-25d4-44bb-9565-f0284136a255")
        @Override
        public String getDescription() {
            // TODO when API available: this.i18nSupport.getDescription(this.stereotype);
            String d = this.stereotype.getNoteContent("ModelerModule", "ModelElement", "description");
            if (isWrong(d, "")) {
                d = "";
            }
            return CoreUi.I18N.getMessage("StereotypeSelector.proposal.desc",
                    this.stereotype.getName(),
                    this.stereotype.getBaseClassName(),
                    this.stereotype.getModule().getName(),
                    d);
            
        }

        @objid ("c90747a5-1d41-4331-89fd-d65d46364566")
        private boolean isWrong(String label, String equiv) {
            return label == null || label.trim().isEmpty() || label.startsWith("%") || label.equals(equiv);
        }

    }

}
