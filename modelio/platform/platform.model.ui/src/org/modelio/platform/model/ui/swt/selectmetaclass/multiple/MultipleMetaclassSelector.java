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
package org.modelio.platform.model.ui.swt.selectmetaclass.multiple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.modelio.platform.model.ui.panels.selectelements.SelectObjectsPanel;
import org.modelio.platform.model.ui.panels.thindialog.ThinPanelDialog;
import org.modelio.platform.model.ui.plugin.CoreUi;
import org.modelio.platform.model.ui.swt.images.MetamodelImageService;
import org.modelio.platform.model.ui.swt.selectmetaclass.IMetaclassSelectorFilter;
import org.modelio.platform.ui.UIImages;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * SelectMetaclass is a reusable component wrapping an SWT Text widget that can be used to select a single metaclass.<br/>
 * The proposal menu is popped whenever one of the following conditions is fulfilled:
 * <ul>
 * <li>the user enters any upper case letter character (as metaclass names always starts with a Capital letter)</li>
 * <li>the user enters CTRL+SPACE which is the standard completion activation sequence</li>
 * </ul>
 * The metaclasses proposed for completion are those defined in the metamodel filtered by IMetaclassSelectorFilter if such a filter is set. Note: SelectMetaclass wraps a SWT Text because inheriting from Text is not possible. Therefore the getTextControl()
 * method is available to reach the inner Text field, typically for layout purposes.
 */
@objid ("716fe6e7-3775-4c94-b006-1eca1f73fd93")
public class MultipleMetaclassSelector {
    @objid ("d8d5cabc-8d54-4d39-9f8b-2e18386906aa")
    private static final char[] AUTO_ACTIVATION_CHARS = "ABCDEFGHIJKLMNOPQRSTUVW".toCharArray();

    @objid ("0b801f53-198c-47cb-a2b6-3365a02d6138")
    private final List<IMultipleMetaclassSelectorListener> listeners = new ArrayList<>(1);

    /**
     * The wrapped Text widget
     */
    @objid ("bd145d57-38e7-43a6-9828-e751a2d245dd")
    private final Text text;


    @mdl.prop
    @objid ("f9e435de-9cbd-47a5-9aef-12055591c9dc")
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

    @objid ("0668c5fc-a5ab-4367-98a8-01bbcb6aacc0")
    private List<MClass> allMetaclasses;

    @objid ("cf4e33f4-9a1e-4b8a-9806-e9f2597299d4")
    private List<MClass> selectedMetaclasses = new ArrayList<>();

    @objid ("298a9718-ba1a-476c-98e8-f2e7668eb47d")
    public  MultipleMetaclassSelector(Composite parent, int style, MMetamodel metamodel) {
        this(parent, style, metamodel, null);
    }

    @objid ("145af9db-a480-4912-95cf-2e166eb052f2")
    public  MultipleMetaclassSelector(Composite parent, int style, MMetamodel metamodel, IMetaclassSelectorFilter filter) {
        this.text = createControl(parent, style);
        this.metaclassFilter = filter;

        this.allMetaclasses = new ArrayList<>(metamodel.getRegisteredMClasses());
        Collections.sort(this.allMetaclasses, new Comparator<MClass>() {
            @Override
            public int compare(MClass o1, MClass o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

    }

    @objid ("6c2c2d5f-a9db-49fe-aad6-ce42b9128366")
    public synchronized void addListener(final IMultipleMetaclassSelectorListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Returns the internal text control. Should be used only for setting layout data.
     * @return
     */
    @objid ("5b969c1b-93a4-4966-a9e2-0cf6cc69fe34")
    public Text getControl() {
        return this.text;
    }

    @objid ("b3e47657-db6c-4873-8b4a-65dfe75380ca")
    public List<MClass> getSelected() {
        return this.selectedMetaclasses;
    }

    @objid ("fb736e2f-87dd-48f3-829d-13b8a8550475")
    public synchronized void removeListener(final IMultipleMetaclassSelectorListener listener) {
        this.listeners.remove(listener);
    }

    @objid ("19dc1a76-ceaf-4bfc-81fc-0fcd128deca1")
    public void setSelected(List<MClass> mClasses) {
        this.selectedMetaclasses.clear();
        if (mClasses != null) {
            this.selectedMetaclasses.addAll(mClasses);
        }
        refreshMetaclassesText();

    }

    @objid ("90cdf83f-17ba-4903-8f95-8324da47c14f")
    private Text createControl(Composite parent, int style) {
        final Text wrappedText = new Text(parent, style | SWT.READ_ONLY);

        // create the decoration for the text component
        final ControlDecoration deco = new ControlDecoration(wrappedText, SWT.CENTER | SWT.RIGHT);

        // set description and image
        deco.setDescriptionText(CoreUi.I18N.getString("MultipleMetaclassSelector.assist.tooltip"));
        deco.setImage(UIImages.ASSIST);

        // always show decoration
        deco.setShowOnlyOnFocus(false);

        wrappedText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDown(MouseEvent e) {
                showProposals();
            }
        });
        return wrappedText;
    }

    @objid ("ca46ff37-4af6-45d9-8e4f-1d21039862c1")
    private void refreshMetaclassesText() {
        switch (this.selectedMetaclasses.size()) {
        case 0:
            this.text.setText("");
            break;
        case 1:
            this.text.setText(this.selectedMetaclasses.get(0).getQualifiedName());
            break;
            default:
                this.text.setText(
                        String.format("%s... (+ %d)",
                                this.selectedMetaclasses.get(0).getQualifiedName(),
                                this.selectedMetaclasses.size()-1));
        }


        this.text.setToolTipText(this.selectedMetaclasses.stream()
                .map((mc)->mc.getQualifiedName())
                .sorted()
                .collect(Collectors.joining("\n")));

    }

    @objid ("bc07f8f3-4033-4ead-8443-eb7b5fef6149")
    private void updateMetaclasses() {
        refreshMetaclassesText();

        for (IMultipleMetaclassSelectorListener l : this.listeners) {
            l.selectMetaclasses(getSelected());
        }

    }

    @objid ("2470b32e-153a-4abd-80f0-75dc50ae3488")
    private void showProposals() {
        IMetaclassSelectorFilter filter = this.metaclassFilter != null ? this.metaclassFilter : (o) -> true;

        Supplier<List<MClass>> candidatesProvider = new Supplier<List<MClass>>() {
            @Override
            public List<MClass> get() {
                return MultipleMetaclassSelector.this.allMetaclasses.stream()
                                                                    .filter((mc) -> filter.accept(mc))
                                                                    .collect(Collectors.toList());
            }
        };

        ILabelProvider labelProvider = new LabelProvider() {
            @Override
            public String getText(Object element) {
                return ((MClass) element).getQualifiedName();
            }

            @Override
            public Image getImage(Object element) {
                return MetamodelImageService.getIcon((MClass) element);
            };
        };

        SelectObjectsPanel<MClass> panel = new SelectObjectsPanel<>(MClass.class, candidatesProvider, labelProvider, true);

        ThinPanelDialog poppingPanel = new ThinPanelDialog(this.text, panel, false) {
            @Override
            public void onClose() {
                updateMetaclasses();
                super.onClose();
            }



        };

        poppingPanel.open();
        poppingPanel.getPanelProvider().setInput(this.selectedMetaclasses);

    }

}
