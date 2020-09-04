/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.module.modelermodule.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.module.modelermodule.i18n.I18nMessageService;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;

/**
 * Swt dialog making the correspondence between Attributes on a Classifier and AttributeLinks on an Instance.
 */
@objid ("0f29dfdc-2884-479b-be8d-ba91bbc64fba")
public class AttributeCreationWizard {
    @objid ("32011680-7dfa-41ee-a817-06cb90184739")
    protected int nbAtt;

    @objid ("6b58a88d-5559-469a-b062-bc83b5ee28de")
    protected Instance instance;

    @objid ("784f8892-3564-430d-8c84-283dad23d60b")
    protected Vector<AttributeLink> toDelete = new Vector<>();

    @objid ("13bd02ff-e2e1-48f2-90bb-26ef70a3d414")
    protected HashMap<String, AttributeLink> links = new HashMap<>();

    @objid ("e3ca34c6-b18b-47c7-8913-d4229e6ae51b")
    protected HashMap<String, Attribute> atts = new HashMap<>();

    @objid ("b097638e-444b-43f2-b9ed-610475982524")
    protected Classifier base;

    @objid ("81c71b15-d142-4f36-a4da-b51ed306cd46")
    protected Vector<Label> column1;

    @objid ("dc7bdb0f-e8ae-42d1-b4f6-a11154e88c85")
    protected Vector<Button> column2;

    @objid ("ced66fc9-39db-4259-a4c0-68791e2f9188")
    protected Vector<Text> column3;

    @objid ("affa5752-c06d-4b2b-88ff-d829cd5f2601")
    protected Shell shell;

    @objid ("88729bd9-6434-4873-ad5e-f7f08acc6087")
    protected Composite composite;

    @objid ("a12928b7-9ffd-4afb-8d85-e73c2b8185e7")
    protected Composite mainGroup;

    /**
     * Default constructor, initializing the classifier and instance to synchronize attributes and attribute links from.
     * 
     * @param parent the parent shell.
     * @param classifier a classifier.
     * @param instance an instance.
     */
    @objid ("bb4b8022-d15d-4f95-9fc0-a9eca82170cc")
    public AttributeCreationWizard(final Shell parent, final Classifier classifier, final Instance instance) {
        this.column1 = new Vector<>();   //Vector of Label
        this.column2 = new Vector<>();  //Vector of Button
        this.column3 = new Vector<>();    //Vector of Text
        
        this.nbAtt = 0;
        this.instance = instance;
        this.base = classifier;
        
        createContents(parent);
    }

    /**
     * Open the window
     */
    @objid ("78462e9f-cf38-46f1-a9b8-f96b0bdf5c6f")
    public void open() {
        ShellHelper.centerShell(this.shell);
        
        this.shell.open();
        this.shell.layout();
        
        final Display display = this.shell.getDisplay();
        while (!this.shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

    @objid ("b6a53648-73de-4f9d-b604-b044ccc460a0")
    protected void updateModel() {
        IUmlModel model = ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel();
        
        for (int i = 0; i < this.nbAtt; i++) {
            AttributeLink link = this.links.get(this.column1.get(i).getText());
        
            if (this.column2.get(i).getSelection()) {
                String value = this.column3.get(i).getText();
                String name = this.column1.get(i).getText();
        
                if (link == null) {
                    link = model.createAttributeLink();
                    link.setBase(this.atts.get(name));
                    link.setAttributed(this.instance);
                    link.setName(name);
                }
        
                link.setValue(value);
            } else {
                if (link != null) {
                    this.toDelete.add(link);
                }
            }
        }
        
        for (int i = 0; i < this.toDelete.size(); i++) {
            this.toDelete.get(i).delete();
        }
    }

    /**
     * Create contents of the window
     */
    @objid ("e2e4dc59-5f34-4b4c-9340-0c5d3bcfd3fe")
    protected void createContents(Shell parent) {
        this.shell = new Shell(parent, SWT.DIALOG_TRIM|SWT.RESIZE);
        
        this.shell.setLayout(new GridLayout());
        this.shell.setText(I18nMessageService.getString("module.gui.attributeWizard.title"));
        
        createLines();
        createButtons();
        
        this.shell.pack();
        this.shell.layout(true);
        this.shell.setSize(Math.min(this.shell.getSize().x + 50, this.shell.getDisplay().getClientArea().width),
                Math.min(this.shell.getSize().y, this.shell.getDisplay().getClientArea().height - 50));
        
        updateValues(this.base, this.instance);
    }

    @objid ("b23921e1-6911-4a28-ae00-23afc1c127fe")
    private List<Attribute> getAttributs(final Classifier cl) {
        List<Attribute> result = new ArrayList<>();
        
        Set<NameSpace> done = new HashSet<>();
        
        List<NameSpace> childs = new ArrayList<>();
        
        result.addAll(cl.getOwnedAttribute());
        
        childs.add(cl);
        done.add(cl);
        
        while (childs.size() > 0){
        
            List<NameSpace> parents = new ArrayList<>();
        
            for (NameSpace child : childs){
                for (Generalization generalization : child.getParent()){
                    NameSpace parent = generalization.getSuperType();
                    if (!done.contains(parent) && (parent instanceof Classifier)){
                        done.add(parent);
                        parents.add(parent);
                        result.addAll(((Classifier)parent).getOwnedAttribute());
                    }
                }
        
                for (InterfaceRealization realization : child.getRealized()){
                    Interface parent = realization.getImplemented();
                    if (!done.contains(parent)){
                        done.add(parent);
                        parents.add(parent);
                        result.addAll(parent.getOwnedAttribute());
                    }
                }
            }
        
            childs = new ArrayList<>();
            childs.addAll(parents);
        }
        return result;
    }

    @objid ("e0d09c24-ede0-42c5-b14f-1deb9f4f409e")
    private void organizeVectors(final Composite group, final Classifier cl, final Instance inst) {
        if (cl != null && inst != null) {
            List<Attribute> oldAtts = getAttributs(cl);
        
        
            boolean heritedAttr = false;
        
            for (Attribute att : oldAtts){
                if (!att.getOwner().equals(cl)){
                    heritedAttr = true;
                    break;
                }
            }
        
            for (Iterator<Attribute> iter = oldAtts.iterator(); iter.hasNext(); this.nbAtt++) {
        
                Attribute att = iter.next();
                this.atts.put(att.getName(), att);
        
                Label att1Label;
                att1Label = new Label(group, SWT.NONE);
                att1Label.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, false, false));
        
                if (heritedAttr)
                    att1Label.setText(att.getOwner().getName() + "::" + att.getName());
                else
                    att1Label.setText(att.getName());
        
                this.column1.add(att1Label);
        
                Button check;
                check = new Button(group, SWT.CHECK);
                check.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
        
                check.setData(Integer.toString(this.nbAtt));
                check.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent evt) {
                        int i = Integer.parseInt(((Button) evt.getSource()).getData().toString());
                        if (AttributeCreationWizard.this.column2.get(i).getSelection()) {
                            AttributeCreationWizard.this.column3.get(i).setEnabled(true);
                        } else {
                            AttributeCreationWizard.this.column3.get(i).setEnabled(false);
                        }
                    }
                });
                this.column2.add(check);
        
                Text value = new Text(group, SWT.BORDER);
                value.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
                this.column3.add(value);
            }
        }
    }

    @objid ("426bdfe2-93c4-4c62-8ff9-4f41f8dba69e")
    private Composite createLines() {
        ScrolledComposite firstScroll = new ScrolledComposite(this.shell, SWT.V_SCROLL);
        firstScroll.setLayout(new GridLayout(1, false));
        firstScroll.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        this.mainGroup = new Composite(firstScroll, SWT.BORDER);
        final GridLayout gridLayout = new GridLayout(3, false);
        this.mainGroup.setLayout(gridLayout);
        this.mainGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        Label nameLabel;
        nameLabel = new Label(this.mainGroup, SWT.NONE);
        nameLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
        nameLabel.setText(I18nMessageService.getString("module.gui.attributeWizard.name"));
        
        Label creationLabel = new Label(this.mainGroup, SWT.NONE);
        creationLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
        creationLabel.setText(I18nMessageService.getString("module.gui.attributeWizard.create"));
        
        Label valueLabel = new Label(this.mainGroup, SWT.NONE);
        valueLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
        valueLabel.setText(I18nMessageService.getString("module.gui.attributeWizard.value"));
        
        organizeVectors(this.mainGroup, this.base, this.instance);
        
        firstScroll.setContent(this.mainGroup);
        firstScroll.setExpandHorizontal(true);
        firstScroll.setExpandVertical(true);
        firstScroll.setMinSize(this.mainGroup.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        return this.mainGroup;
    }

    @objid ("e77bfbd7-5aa2-4095-9255-ea63448b3d8f")
    private void updateValues(final Classifier cl, final Instance inst) {
        if (cl != null && inst != null) {
            List<Attribute> oldAtts = cl.getOwnedAttribute();
            Vector<AttributeLink> oldLinks = new Vector<>();
        
            for (Iterator<AttributeLink> iter = inst.getSlot().iterator(); iter.hasNext();) {
                oldLinks.add(iter.next());
            }
        
            int i = 0;
            for (Iterator<Attribute> iter = oldAtts.iterator(); iter.hasNext(); i++) {
                Attribute att = iter.next();
        
                boolean found = false;
                for (int j = 0; j < oldLinks.size(); j++) {
                    AttributeLink link = oldLinks.get(j);
        
                    if (link.getBase() != null && att.equals(link.getBase())) {
                        if (found) {
                            this.toDelete.add(link);
                        } else {
                            this.column2.get(i).setSelection(true);
                            this.column3.get(i).setText(link.getValue());
        
                            found = true;
                            this.links.put(att.getName(), link);
                        }
        
                        oldLinks.remove(link);
                        j--;
                    }
                }
        
                if (!found) {
                    this.links.put(att.getName(), null);
                    this.column3.get(i).setEnabled(false);
                }
            }
        
            for (int j = 0; j < oldLinks.size(); j++) {
                AttributeLink link = oldLinks.get(j);
        
                this.toDelete.add(link);
            }
        }
    }

    @objid ("d540d6d9-3177-4c1e-9468-41cc194c65fa")
    private void createButtons() {
        this.composite = new Composite(this.shell, SWT.NONE);
        final RowLayout rowLayout = new RowLayout();
        rowLayout.justify = true;
        this.composite.setLayout(rowLayout);
        this.composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
        
        final Composite composite_2 = new Composite(this.composite, SWT.NONE);
        composite_2.setLayout(new FillLayout());
        
        final Button okButton = new Button(composite_2, SWT.NONE);
        okButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                updateModel();
                dispose();
            }
        });
        okButton.setText(I18nMessageService.getString("module.gui.ok"));
        
        Button cancelButton;
        cancelButton = new Button(composite_2, SWT.NONE);
        cancelButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                dispose();
            }
        });
        cancelButton.setText(I18nMessageService.getString("module.gui.cancel"));
    }

    @objid ("5e084ee0-7d5e-4e66-b321-02f9275d9294")
    protected void dispose() {
        this.shell.dispose();
    }

}
