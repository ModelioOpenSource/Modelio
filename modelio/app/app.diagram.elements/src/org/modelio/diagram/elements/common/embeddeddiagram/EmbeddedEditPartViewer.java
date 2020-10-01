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

package org.modelio.diagram.elements.common.embeddeddiagram;

import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.Handle;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.SelectionManager;
import org.eclipse.gef.dnd.TransferDragSourceListener;
import org.eclipse.gef.dnd.TransferDropTargetListener;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Edit part viewer for embedded diagrams.
 * <p>
 * Embedded diagrams edit part viewer redirects all calls to the parent diagram viewer except:
 * <ul>
 * <li>{@link #setProperty(String, Object)} ignores the call
 * <li>it instantiate its own edit part registry with a local layer manager.
 * <li>some setters that should not be called throw {@link UnsupportedOperationException}.
 * </ul>
 * 
 * @author cma
 * @since 3.7
 */
@objid ("c2077e4e-67b5-4534-bddd-c8b4d1b97f84")
@SuppressWarnings ("deprecation")
class EmbeddedEditPartViewer implements GraphicalViewer {
    @objid ("e041b9fe-0ff7-4028-9a90-87b1c97566ae")
    private final EmbeddedEditPartRegistry editPartRegistry;

    @objid ("8cb45a1d-7b30-4735-a110-6ba368d3cc9c")
    private final GraphicalViewer parentViewer;

    @objid ("d07a4bdf-712f-4e2a-899a-80272d5d3fed")
    public EmbeddedEditPartViewer(EmbeddedDiagramRootEditPart rootEp) {
        Objects.requireNonNull(rootEp);
        
        this.parentViewer = (GraphicalViewer) Objects.requireNonNull(rootEp.getParent().getRoot().getViewer());
        this.editPartRegistry = new EmbeddedEditPartRegistry(this.parentViewer.getEditPartRegistry(), new EmbeddedLayerManager(rootEp));
    }

    @objid ("6778bac8-98fb-4553-9f43-e62bb9a90e88")
    @Override
    public void addDragSourceListener(TransferDragSourceListener listener) {
        getParentViewer().addDragSourceListener(listener);
    }

    @objid ("304141ed-b660-467e-bc34-73a775b7c858")
    @Override
    public void addDragSourceListener(org.eclipse.jface.util.TransferDragSourceListener listener) {
        getParentViewer().addDragSourceListener(listener);
    }

    @objid ("446fa5de-11b4-437a-93a3-9cd321bcd161")
    @Override
    public void addDropTargetListener(TransferDropTargetListener listener) {
        getParentViewer().addDropTargetListener(listener);
    }

    @objid ("83d37775-3330-4f0a-8692-18ded6b086fa")
    @Override
    public void addDropTargetListener(org.eclipse.jface.util.TransferDropTargetListener listener) {
        getParentViewer().addDropTargetListener(listener);
    }

    @objid ("a98d553c-1cd0-4208-98bb-59779689d708")
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        getParentViewer().addPropertyChangeListener(listener);
    }

    @objid ("755e2248-1268-49aa-90bb-f742506f2ef6")
    @Override
    public void addSelectionChangedListener(ISelectionChangedListener listener) {
        getParentViewer().addSelectionChangedListener(listener);
    }

    @objid ("af67fcb8-b21b-4830-8de4-bcc89d64e6ae")
    @Override
    public void appendSelection(EditPart editpart) {
        getParentViewer().appendSelection(editpart);
    }

    @objid ("8a4d7d3b-c043-45d4-88ab-21416283fb9e")
    @Override
    public Control createControl(Composite composite) {
        return getParentViewer().createControl(composite);
    }

    @objid ("afc87b07-9d59-4c6d-bed3-1068730b862a")
    @Override
    public void deselect(EditPart editpart) {
        getParentViewer().deselect(editpart);
    }

    @objid ("91dbfc08-36a4-4c96-b2bd-5fb42e46cfed")
    @Override
    public void deselectAll() {
        getParentViewer().deselectAll();
    }

    @objid ("22455485-c1af-4461-8ecc-ad09cf414295")
    @Override
    public Handle findHandleAt(Point p) {
        return getParentViewer().findHandleAt(p);
    }

    @objid ("9a15ef66-a447-4696-bfb0-d206ab416b65")
    @Override
    public EditPart findObjectAt(Point location) {
        return getParentViewer().findObjectAt(location);
    }

    @objid ("192467f7-5e71-4f6b-98bc-de48beb3461b")
    @Override
    public EditPart findObjectAtExcluding(Point location, Collection exclusionSet) {
        return getParentViewer().findObjectAtExcluding(location, exclusionSet);
    }

    @objid ("d5f9f7c4-d5a1-4f91-b942-7d01eb931ae4")
    @Override
    public EditPart findObjectAtExcluding(Point location, Collection exclusionSet, Conditional conditional) {
        return getParentViewer().findObjectAtExcluding(location, exclusionSet, conditional);
    }

    @objid ("452c02e2-5237-4d6d-b9ce-7ac2210b14ee")
    @Override
    public void flush() {
        getParentViewer().flush();
    }

    @objid ("ca2ee308-9c98-406d-a808-d738f664e949")
    @Override
    public EditPart getContents() {
        return getParentViewer().getContents();
    }

    @objid ("c24fd055-ee95-4316-b826-d3d194ce8c67")
    @Override
    public MenuManager getContextMenu() {
        return getParentViewer().getContextMenu();
    }

    @objid ("f6bb4ba6-e042-4580-9ac8-987f984980b2")
    @Override
    public Control getControl() {
        return getParentViewer().getControl();
    }

    @objid ("515c9d42-2fb6-4448-8a24-407289ec51e0")
    @Override
    public EditDomain getEditDomain() {
        return getParentViewer().getEditDomain();
    }

    @objid ("9a677b91-e0ed-4b90-a3b3-53e0f535523c")
    @Override
    public EditPartFactory getEditPartFactory() {
        return getParentViewer().getEditPartFactory();
    }

    @objid ("c4c34558-5890-4758-9234-2a31fb65e035")
    @Override
    public Map getEditPartRegistry() {
        return this.editPartRegistry;
    }

    @objid ("5bee9080-c96a-4de4-82ea-11bd803e71b4")
    @Override
    public EditPart getFocusEditPart() {
        return getParentViewer().getFocusEditPart();
    }

    @objid ("d6ada90a-16d5-44fd-b080-9d0904de4196")
    @Override
    public KeyHandler getKeyHandler() {
        return getParentViewer().getKeyHandler();
    }

    @objid ("bccf64e2-e54d-49a7-9bb2-71bc5bc57f9a")
    public GraphicalViewer getParentViewer() {
        return this.parentViewer;
    }

    @objid ("ff17a30f-3331-45d8-93b7-35ecccd6a89a")
    @Override
    public Object getProperty(String key) {
        return getParentViewer().getProperty(key);
    }

    @objid ("a3628b7f-9320-451e-8889-56ee6569dc7c")
    @Override
    public ResourceManager getResourceManager() {
        return getParentViewer().getResourceManager();
    }

    @objid ("302fddbd-b05c-4ed7-8c81-bbeb91694b05")
    @Override
    public RootEditPart getRootEditPart() {
        return getParentViewer().getRootEditPart();
    }

    @objid ("92aa1e21-2d70-4e03-9b37-145edfd0917f")
    @Override
    public List getSelectedEditParts() {
        return getParentViewer().getSelectedEditParts();
    }

    @objid ("87831c1d-3a4e-4944-8cf9-f680782e7e2a")
    @Override
    public ISelection getSelection() {
        return getParentViewer().getSelection();
    }

    @objid ("9833cb00-f5e2-41fe-9be4-4fd5f77a46f0")
    @Override
    public SelectionManager getSelectionManager() {
        return getParentViewer().getSelectionManager();
    }

    @objid ("a01ec74a-38ed-47ed-9118-3c86a0ebd8ff")
    @Override
    public Map getVisualPartMap() {
        return getParentViewer().getVisualPartMap();
    }

    @objid ("f36916a6-5dff-4478-a031-131d32cbfeb6")
    @Override
    public void registerAccessibleEditPart(AccessibleEditPart acc) {
        getParentViewer().registerAccessibleEditPart(acc);
    }

    @objid ("261f5ed6-222a-4afc-9093-75a5058dc7de")
    @Override
    public void removeDragSourceListener(TransferDragSourceListener listener) {
        getParentViewer().removeDragSourceListener(listener);
    }

    @objid ("8f0676e3-e6bb-40c7-ab60-f63ea148bcad")
    @Override
    public void removeDragSourceListener(org.eclipse.jface.util.TransferDragSourceListener listener) {
        getParentViewer().removeDragSourceListener(listener);
    }

    @objid ("aaa8d071-d2f9-48d1-ba68-b2fae5955041")
    @Override
    public void removeDropTargetListener(TransferDropTargetListener listener) {
        getParentViewer().removeDropTargetListener(listener);
    }

    @objid ("f9e9090d-85d7-4c66-a8fc-abcd776b0497")
    @Override
    public void removeDropTargetListener(org.eclipse.jface.util.TransferDropTargetListener listener) {
        getParentViewer().removeDropTargetListener(listener);
    }

    @objid ("55c7d883-2260-4293-8315-7d59af7cff5c")
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        getParentViewer().removePropertyChangeListener(listener);
    }

    @objid ("4addff84-4252-44ea-aa25-141d949274c7")
    @Override
    public void removeSelectionChangedListener(ISelectionChangedListener listener) {
        getParentViewer().removeSelectionChangedListener(listener);
    }

    @objid ("a697dbe6-0b09-44e8-a34a-17637bca429d")
    @Override
    public void reveal(EditPart editpart) {
        getParentViewer().reveal(editpart);
    }

    @objid ("7278b74d-20b2-4a56-948a-1a5815a353bd")
    @Override
    public void select(EditPart editpart) {
        getParentViewer().select(editpart);
    }

    @objid ("308a192d-af94-4f87-8e55-40fe8d0603b0")
    @Override
    public void setContents(EditPart editpart) {
        throw new UnsupportedOperationException();
    }

    @objid ("25b2cf5d-0d0a-4901-95c3-728a06deb2b2")
    @Override
    public void setContents(Object contents) {
        throw new UnsupportedOperationException();
    }

    @objid ("9036b9d5-ad8b-4b94-b9ec-19baab7d781d")
    @Override
    public void setContextMenu(MenuManager contextMenu) {
        getParentViewer().setContextMenu(contextMenu);
    }

    @objid ("75a4fc5e-a110-49e4-9c4b-58949703730c")
    @Override
    public void setControl(Control control) {
        throw new UnsupportedOperationException();
    }

    @objid ("988cf28b-a27d-4de9-8329-4ac364b8238b")
    @Override
    public void setCursor(Cursor cursor) {
        getParentViewer().setCursor(cursor);
    }

    @objid ("82cdb3b8-3f5f-49a5-94fe-26eb1aa83819")
    @Override
    public void setEditDomain(EditDomain domain) {
        throw new UnsupportedOperationException();
    }

    @objid ("5e8d66c1-0232-4669-ace6-6d9a46846844")
    @Override
    public void setEditPartFactory(EditPartFactory factory) {
        throw new UnsupportedOperationException();
    }

    @objid ("c85b9334-c533-4a5c-927b-3312bdb20d7f")
    @Override
    public void setFocus(EditPart focus) {
        getParentViewer().setFocus(focus);
    }

    @objid ("7f7530c0-edc6-4f6c-b76c-95ed17a70dc2")
    @Override
    public void setKeyHandler(KeyHandler keyHandler) {
        throw new UnsupportedOperationException();
    }

    /**
     * Ignores the call.
     */
    @objid ("f42114b4-6f16-4df6-bb35-4847e8bb10a8")
    @Override
    public void setProperty(String propertyName, Object value) {
        // Ignore setProperty, just in case.
    }

    @objid ("1d352349-d263-44f2-94d0-37d5a53ab908")
    @Override
    public void setRootEditPart(RootEditPart root) {
        throw new UnsupportedOperationException();
    }

    @objid ("b8dc84ce-f6de-45bb-980a-4c72205a4e6f")
    @Override
    public void setRouteEventsToEditDomain(boolean value) {
        getParentViewer().setRouteEventsToEditDomain(value);
    }

    @objid ("45f2dfeb-b89c-4296-83ea-3192207d53fb")
    @Override
    public void setSelection(ISelection selection) {
        getParentViewer().setSelection(selection);
    }

    @objid ("a2d4a025-881a-485b-9cf4-7da254441f10")
    @Override
    public void setSelectionManager(SelectionManager manager) {
        throw new UnsupportedOperationException();
    }

    @objid ("735d3efe-5871-4ebb-8bcd-8063dd568b2c")
    @Override
    public void unregisterAccessibleEditPart(AccessibleEditPart acc) {
        getParentViewer().unregisterAccessibleEditPart(acc);
    }

}
