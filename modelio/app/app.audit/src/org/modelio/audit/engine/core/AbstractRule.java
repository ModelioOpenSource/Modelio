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
package org.modelio.audit.engine.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.service.AuditSeverity;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Abstract base implementation of IRule used on Modelio 5.3.0 and before.
 * 
 * @deprecated implement {@link AbstractRuleV531} instead.
 */
@objid ("ef11eaf1-c4ec-462c-8c03-4ef2d9d345bb")
@Deprecated(since = "5.3.1")
public abstract class AbstractRule implements IRule {
    @objid ("fbc5c37a-9f9c-46f9-a0a3-dae8f6ba37dc")
    protected int triggers = 0;

    /**
     * For performance's sake, IRules implementations have the current severity too.
     */
    @objid ("722e8b87-dd35-4135-a36e-409f9d37f58a")
    private AuditSeverity severity = AuditSeverity.AuditError;

    /**
     * Get the {@link IControl} to run to validate the rule when the given element is created.
     * @param element the created element.
     * @return the {@link IControl} to run to validate the rule.
     * @deprecated Implement {@link #postCreateControls(IRuleControlPoster, MObject)} instead
     */
    @objid ("caa559ca-5c8a-4c24-a8cd-455b7588c5cf")
    @Deprecated(since = "5.3.1", forRemoval = true)
    protected IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * @deprecated Implement {@link #postMoveControls(IRuleControlPoster, IElementMovedEvent)}
     */
    @objid ("1dcf90a4-255c-4a46-b4ef-1977fbb0c180")
    @Deprecated(since = "5.3.1", forRemoval = true)
    protected IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Get the {@link IControl} to run to validate the rule when the given element is modified.
     * @param element the modified element.
     * @return the {@link IControl} to run to validate the rule.
     * @deprecated Implement {@link #postUpdateControls(IRuleControlPoster, MObject)} ,  {@link #postDeleteControls(IRuleControlPoster, MObject, MObject)},
     * {@link #postDeleteControls(IRuleControlPoster, MObject, MObject)} instead.
     */
    @objid ("c41e6eac-12e8-4559-bab8-208d6c0fa5b7")
    @Deprecated(since = "5.3.1", forRemoval = true)
    protected IControl getUpdateControl(MObject element) {
        return null;
    }

    @objid ("7f85af46-9ec7-4308-9214-de9fc2199b69")
    @Override
    public abstract String getRuleId();

    @objid ("b49baaec-e5ac-43ba-8d3a-6bfb39e350c9")
    public final void setTriggers(int value) {
        this.triggers = value;
    }

    @objid ("075fbbfe-bdfa-49d6-93ab-f5b0f93ad361")
    @Override
    public final AuditSeverity getSeverity() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.severity;
    }

    @objid ("7eddfb59-7a43-48ed-ad82-094d9c333324")
    @Override
    public final void setSeverity(AuditSeverity value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.severity = value;
        
    }

    /**
     * Get the {@link IControl} to run to validate the rule when an audited element is deleted.
     * <h2>Important:</h2>
     * The passed element is the <b>composition parent</b> of the deleted element.
     * @param deletedElementOwner the <b>composition parent</b> of the deleted element.
     * @return the {@link IControl} to run to validate the rule.
     * @deprecated since 5.3.1, implement {@link #postDeleteControls(IRuleControlPoster, MObject, MObject)} instead.
     */
    @objid ("2fc55880-af76-41ac-85e7-43fb87c38164")
    @Deprecated(since = "5.3.1", forRemoval = true)
    protected IControl getDeleteControl(final MObject deletedElementOwner) {
        return null;
    }

    /**
     * Get the {@link IControl} to run to validate the rule when an audited element is deleted.
     * <p>
     * The control will be run on the 'ownerElement'.
     * @param poster the service to use to post audit checks on elements
     * @param deletedElement the <b>deleted</b>element.
     * @param ownerElement the <b>composition parent</b> of the deleted element.
     * @since 5.3.1
     */
    @objid ("dd1b2b89-becb-4cd1-a691-fccf4652371c")
    @Override
    public void postDeleteControls(IRuleControlPoster poster, final MObject deletedElement, final MObject ownerElement) {
        poster.postControl(getDeleteControl(ownerElement), ownerElement);
    }

    /**
     * Get the {@link IControl} to run to validate the rule when an audited element is deleted.
     * <p>
     * The control will be run on the 'ownerElement'.
     * @param poster the service to use to post audit checks on elements
     * @param createdElement the <b>deleted</b>element.
     * @since 5.3.1
     */
    @objid ("2c19a347-96c4-4531-b3df-c5e4f4067cc9")
    @Override
    public void postCreateControls(IRuleControlPoster poster, final MObject createdElement) {
        poster.postControl(getCreationControl(createdElement), createdElement);
    }

    /**
     * Get the {@link IControl} to run to validate the rule when an audited element is deleted.
     * <p>
     * The control will be run on the 'ownerElement'.
     * @param poster the service to use to post audit checks on elements
     * @param updatedElement the <b>deleted</b>element.
     * @since 5.3.1
     */
    @objid ("e15eba2c-8733-440e-809c-e2c726f8ea94")
    @Override
    public void postUpdateControls(IRuleControlPoster poster, final MObject updatedElement) {
        poster.postControl(getUpdateControl(updatedElement), updatedElement);
    }

    /**
     * Post the {@link IControl} to run to validate the rule when an audited element moved.
     * @param poster the service to use to post audit checks on elements
     * @param moveEvent the move event.
     * @since 5.3.1
     */
    @objid ("bb778248-4ec1-48ef-b4c7-5be7f7a5d300")
    @Override
    public void postMoveControls(IRuleControlPoster poster, IElementMovedEvent moveEvent) {
        poster.postControl(getMoveControl(moveEvent), moveEvent.getMovedElement());
    }

    @objid ("c57d953d-a029-4295-82ae-d97a4913b468")
    @Override
    @Deprecated
    public final void postChildRemovedFromControls(IRuleControlPoster poster, MObject removedElement, MObject oldParent) {
        poster.postControl( getUpdateControl(oldParent), oldParent);
    }

}
