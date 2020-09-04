/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.model.browser.view.handlers.history;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Display;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.vcore.session.api.model.change.IElementDeletedEvent;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;
import org.modelio.vcore.session.api.model.change.IModelChangeListener;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Manage the Selection history in the application.
 */
@objid ("aee105bb-2154-4af4-bd12-2a9437252b4c")
public class SelectionHistory implements IModelChangeListener {
    @objid ("cf4b76b4-fc43-4957-a9c0-036a31c9e953")
    protected boolean isForward;

    @objid ("25ec1d07-ad19-4556-ab2f-161a05eb8631")
    protected LinkedList<MObject> navigationHistory = new LinkedList<>();

    @objid ("167ca1d1-849c-47a6-94d4-e651417ba6be")
    protected ListIterator<MObject> currentSelection;

    @objid ("2c2deb53-33a4-4499-be21-7138be0b9d96")
    public SelectionHistory() {
        super();
    }

    /**
     * On selection we add the selected element in the History list. If the current selection is not the last element of
     * the history all the elements that are after the current element are removed from the history.
     */
    @objid ("5bb95410-0d09-4743-80f5-319ae462ee53")
    public void update(List<MObject> selectedElements) {
        // We consider the selection only if there is one element selected.
        if (selectedElements.size() != 1) {
            return;
        }
        
        MObject element = selectedElements.get(0);
        
        // Compute index for the current element
        int nextIndex = this.currentSelection != null ? this.currentSelection.nextIndex() : -1;
        if (this.isForward) {
            nextIndex--;
        }
        
        // Ignore update if the selected element is already the current one
        if (nextIndex >= 0 && nextIndex < this.navigationHistory.size() && element.equals(this.navigationHistory.get(nextIndex))) {
            return;
        }
        
        // Remove elements that are after the current selection in the history:
        if (this.currentSelection != null) {
            int pos = nextIndex;
            removeAfter(pos);
        }
        
        // Add the selected element to the history:
        
        // We add it only if it is not already the last element of the history.
        if (this.navigationHistory.size() == 0 ||
                this.navigationHistory.size() > 0 &&
                        !this.navigationHistory.peekLast().equals(element)) {
            addElement(element);
        }
        
        // Set the iterator to the added element.
        int index = this.navigationHistory.lastIndexOf(element);
        this.currentSelection = this.navigationHistory.listIterator(index);
        
        this.isForward = false;
    }

    @objid ("f9e74de4-48d4-498f-a16d-9d36eebdc4a0")
    private void addElement(MObject element) {
        if (this.navigationHistory.size() == 250) {
            this.navigationHistory.removeFirst();
        }
        this.navigationHistory.offerLast(element);
    }

    @objid ("28b1d647-012d-41ac-b470-62d790856c62")
    public void selectPreviousSelection(IModelioNavigationService selectionService) {
        if (this.isForward && this.currentSelection.hasPrevious()) {
            this.currentSelection.previous();
            this.isForward = false;
        }
        
        if (this.currentSelection.hasPrevious()) {
            fireSelection(selectionService, this.currentSelection.previous());
        }
    }

    @objid ("8d5e7f26-6667-4757-aff5-2d7cb69b621c")
    public void selectNextSelection(IModelioNavigationService selectionService) {
        if (!this.isForward && this.currentSelection.hasNext()) {
            this.currentSelection.next();
            this.isForward = true;
        }
        
        if (this.currentSelection.hasNext()) {
            fireSelection(selectionService, this.currentSelection.next());
        }
    }

    @objid ("7a814a2c-8cec-4027-af80-910ad7947e64")
    private void removeAfter(int index) {
        while (this.navigationHistory.size() > index + 1) {
            this.navigationHistory.removeLast();
        }
    }

    @objid ("d8608ba7-be01-4a7d-a88b-87c8544066a9")
    @Override
    public void modelChanged(IModelChangeEvent event) {
        final List<IElementDeletedEvent> deletedEvents = event.getDeleteEvents();
        
        // Re enter the UI thread
        Display display = Display.getDefault();
        if (display != null) {
            display.asyncExec(new Runnable() {
                @Override
                public void run() {
                    if (deletedEvents.size() > 0) {
                        for (IElementDeletedEvent deletedEvent : deletedEvents) {
                            MObject element = deletedEvent.getDeletedElement();
                            SelectionHistory.this.navigationHistory.remove(element);
                        }
        
                        // Set the iterator to the last element.
                        int index = SelectionHistory.this.navigationHistory.size();
                        SelectionHistory.this.currentSelection = SelectionHistory.this.navigationHistory.listIterator(index);
                        SelectionHistory.this.isForward = false;
                    }
                }
            });
        }
    }

    @objid ("15284e35-43fa-4fa1-9a5b-0434e2d2a627")
    public void clear() {
        this.navigationHistory.clear();
        this.currentSelection = null;
        this.isForward = false;
    }

    @objid ("20ba36fb-3545-4cfa-a013-b34b7f6b9d19")
    public boolean hasNext() {
        if (this.currentSelection == null) {
            return false;
        }
        
        int nextIndex = this.currentSelection.nextIndex();
        if (this.isForward) {
            nextIndex--;
        }
        return nextIndex >= 0 && nextIndex < this.navigationHistory.size() - 1;
    }

    @objid ("2f2945d8-c6d9-465a-86d4-b3586c395869")
    public boolean hasPrevious() {
        if (this.currentSelection == null) {
            return false;
        }
        return this.currentSelection.hasPrevious();
    }

    @objid ("c9431d09-d90e-49b6-8601-f285d0857db7")
    private void fireSelection(IModelioNavigationService selectionService, MObject element) {
        selectionService.fireNavigate(element);
    }

}
