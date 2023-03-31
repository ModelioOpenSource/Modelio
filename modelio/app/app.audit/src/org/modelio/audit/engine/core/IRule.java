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
 * Audit rule definition.
 */
@objid ("a02279a2-cf77-4143-9bd0-398931cd28d7")
public interface IRule {
    /**
     * @return the rule identifier. Should look like "R0000" .
     */
    @objid ("1be8a60a-4486-4e17-8237-80a4e87a5520")
    String getRuleId();

    /**
     * @return the severity of the rule failure.
     */
    @objid ("aed0116c-6438-4f5a-bab9-4eae922c13c0")
    AuditSeverity getSeverity();

    /**
     * @param severity the new rule severity.
     */
    @objid ("32b3ee8f-e752-4dd0-9e8e-a6425a1859d9")
    void setSeverity(AuditSeverity severity);

    /**
     * Get the {@link IControl} to run to validate the rule when an audited element is deleted.
     * <p>
     * The control will be run on the 'ownerElement'.
     * @param poster the service to use to post audit checks on elements
     * @param deletedElement the <b>deleted</b>element.
     * @param ownerElement the <b>composition parent</b> of the deleted element.
     * @since 5.3.1
     */
    @objid ("44d0e103-294d-4976-8405-d551a80e86e0")
    void postDeleteControls(IRuleControlPoster poster, final MObject deletedElement, final MObject ownerElement);

    /**
     * Get the {@link IControl} to run to validate the rule when an audited element is deleted.
     * <p>
     * The control will be run on the 'ownerElement'.
     * @param poster the service to use to post audit checks on elements
     * @param createdElement the <b>deleted</b>element.
     * @since 5.3.1
     */
    @objid ("a9cda8cd-d23b-4a3e-960c-ab2b0bbbad16")
    void postCreateControls(IRuleControlPoster poster, final MObject createdElement);

    /**
     * Get the {@link IControl} to run to validate the rule when an audited element is deleted.
     * <p>
     * The control will be run on the 'ownerElement'.
     * @param poster the service to use to post audit checks on elements
     * @param updatedElement the <b>deleted</b>element.
     * @since 5.3.1
     */
    @objid ("09d01f8c-4b68-4dd8-aed7-96efccf12e68")
    void postUpdateControls(IRuleControlPoster poster, final MObject updatedElement);

    /**
     * Get the {@link IControl} to run to validate the rule when an audited element moved.
     * <p>
     * The control will be run on the 'ownerElement'.
     * @param poster the service to use to post audit checks on elements
     * @param moveEvent the move event.
     * @since 5.3.1
     */
    @objid ("3e52862d-7d4e-405d-befc-4d5ace83f230")
    void postMoveControls(IRuleControlPoster poster, IElementMovedEvent moveEvent);

    /**
     * Posts {@link IControl} to run to validate the rule when an audited element moved or deleted from its parent element.
     * <p>
     * The control will be run on the 'ownerElement'.
     * @param poster the service to use to post audit checks on elements
     * @param removedElement the element removed from its owner. It may be a deleted element
     * @param oldParent the previous owner of the (re)moved element.
     * @since 5.3.1
     * @deprecated Only here for compatibility with < 5.3.1 behavior. Implement {@link #postMoveControls(IRuleControlPoster, IElementMovedEvent)} instead.
     * To be deleted once no IRule implementation rely on getUpdateControl(...) being called when an element is moved from its old owner.
     */
    @objid ("42bb0404-8e47-4c1b-b569-78c01f5ed728")
    @Deprecated(since = "5.3.1", forRemoval = true)
    void postChildRemovedFromControls(IRuleControlPoster poster, MObject removedElement, MObject oldParent);
}

