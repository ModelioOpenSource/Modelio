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

package org.modelio.editors.richnote.gui.creation.doctype;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("b4334473-2d25-458f-abfb-e2234fdda53b")
public class DocTypeChooserDriver {
    @objid ("10db42d5-989a-4fcd-bf14-9c95eaebbb32")
    private ModelElement element;

    @objid ("7e09eb6c-d658-4712-be2c-895fef3f192b")
    private DocTypeChooserModel leftModel;

    @objid ("7282cd16-1e44-4087-92e7-2fc713a65136")
    private TreeViewer leftViewer;

    @objid ("5fb9cff5-6ca7-45cb-b336-0627f8b67d63")
    private DocTypeChooserSelectionListener selectionListener = null;

    @objid ("3bc31b48-5f40-4407-a51a-4e7793700b53")
    public DocTypeChooserDriver() {
        this.selectionListener = new DocTypeChooserSelectionListener();
    }

    @objid ("c338c8ab-b664-4e4b-b4c5-fdd6f464ca83")
    public StructuredViewer createViewer(Composite parent) {
        this.leftViewer = new TreeViewer(parent, SWT.BORDER);
        this.leftViewer.setContentProvider(new DocTypeChooserContentProvider());
        this.leftViewer.setLabelProvider(new DocTypeChooserLabelProvider());
        this.leftViewer.setComparator(new DocTypeChooserComparator());
        this.leftViewer.setAutoExpandLevel(AbstractTreeViewer.ALL_LEVELS);
        this.leftViewer.addSelectionChangedListener(this.selectionListener);
        return this.leftViewer;
    }

    @objid ("eeb084fe-68cf-4855-b389-4ac77eb43e72")
    public ResourceType getSelection() {
        for (Object obj : ((IStructuredSelection) this.leftViewer.getSelection()).toArray()) {
            AdapterRichNoteType adapter = (AdapterRichNoteType) obj;
        
            ResourceType noteType = adapter.getDocType();
            return noteType;
        }
        return null;
    }

    @objid ("2844a083-10db-4a4a-be10-df2698172c61")
    public void init(final MObject anElement) {
        if (anElement instanceof ModelElement && !anElement.equals(this.element)) {
            this.element = (ModelElement) anElement;
            this.leftModel = new DocTypeChooserModel(this.element);
            this.leftViewer.setInput(this.leftModel);
            setExpandedState();
        }
    }

    @objid ("96b96b79-555c-405b-a322-bbe5c0832c64")
    @Override
    protected void finalize() throws Throwable {
        this.leftViewer.removeSelectionChangedListener(this.selectionListener);
        super.finalize();
    }

    @objid ("7f0f13f2-e157-4d2c-85d6-927dd60c39dd")
    private void setExpandedState() {
        this.leftViewer.setAutoExpandLevel(3);
    }

    @objid ("d78887c9-d330-4015-8061-c7948068f952")
    public void selectInViewer(ResourceType type) {
        for (AdapterModule m : this.leftModel.getMdacAdapters().values()) {
            for (IAdaptable ad : m.getAdapters()) {
                ResourceType rnt = ad.getAdapter(ResourceType.class);
                if (Objects.equals(rnt, type)) {
                    this.leftViewer.setSelection(new StructuredSelection(ad));
                }
            }
        }
    }

    @objid ("efc4a075-75b9-4471-b599-9a603ff8cbd9")
    public ResourceType getDefaultDocumentType() {
        for (AdapterModule m : this.leftModel.getMdacAdapters().values()) {
            for (IAdaptable ad : m.getAdapters()) {
                ResourceType rnt = ad.getAdapter(ResourceType.class);
                if (rnt != null && Objects.equals(rnt.getName(), "other")) {
                    return rnt;
                }
            }
        }
        return null;
    }

    @objid ("f768e58d-a4e1-4fcf-9558-214431fe928a")
    private class DocTypeChooserSelectionListener implements ISelectionChangedListener {
        @objid ("3653f3c7-653e-4bd8-ad76-5026eb6b8047")
        private boolean enable = true;

        @objid ("f4ba7f4c-663c-4b43-b66d-fd396fa28c42")
        @Override
        @SuppressWarnings ({ "synthetic-access" })
        public void selectionChanged(SelectionChangedEvent event) {
            if (this.enable) {
                this.enable = false;
                ISelection selection = event.getSelection();
                if (selection instanceof IStructuredSelection) {
                    IStructuredSelection structuredSelection = (IStructuredSelection) selection;
                    List<Object> selectedAdapters = structuredSelection.toList();
            
                    List<AdapterRichNoteType> selectedNoteTypeAdapters = getNoteTypeAdapters(selectedAdapters);
            
                    DocTypeChooserDriver.this.leftViewer.setSelection(new StructuredSelection(selectedNoteTypeAdapters));
                }
                this.enable = true;
            }
        }

        @objid ("2749e4fb-7fd5-4e33-8025-3f2127f5ed24")
        private List<AdapterRichNoteType> getNoteTypeAdapters(List<Object> selectedAdapters) {
            List<AdapterRichNoteType> adapters = new ArrayList<>();
            
            for (Object obj : selectedAdapters) {
                if (obj instanceof AdapterRichNoteType) {
                    adapters.add((AdapterRichNoteType) obj);
                }
            }
            return adapters;
        }

        @objid ("3a12c6f7-b594-414e-ba12-2aded9f0dba8")
        public DocTypeChooserSelectionListener() {
            super();
        }

    }

}
