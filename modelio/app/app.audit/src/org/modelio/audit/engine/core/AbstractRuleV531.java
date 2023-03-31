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
 * Abstract base implementation of {@link IRule} to use since Modelio 5.3.1 .
 * <p>
 * Subclasses must implement {@link #getRuleId()} and should redefine some of:<ul>
 * <li>{@link #postCreateControls(IRuleControlPoster, MObject)}
 * <li>{@link #postUpdateControls(IRuleControlPoster, MObject)}
 * <li>{@link #postMoveControls(IRuleControlPoster, IElementMovedEvent)}
 * <li>{@link #postDeleteControls(IRuleControlPoster, MObject, MObject)}
 * </ul>
 * @author cmarin
 * @since 5.3.1
 */
@objid ("5342df0c-a273-4070-a5d9-a4f976d1a299")
public abstract class AbstractRuleV531 implements IRule {
    /**
     * For performance's sake, IRules implementations have the current severity too.
     */
    @objid ("95333303-cda5-4369-bc0a-840dc0f962dc")
    private AuditSeverity severity = AuditSeverity.AuditError;

    @objid ("9eabf95c-598c-462e-a24f-67d2bdd1b6e3")
    @Override
    public abstract String getRuleId();

    @objid ("00217a6c-0426-474e-89a8-015f66eeb7ec")
    @Override
    public final AuditSeverity getSeverity() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.severity;
    }

    @objid ("d1a2bc5d-9eb7-4b20-901b-2273a70c81e1")
    @Override
    public final void setSeverity(AuditSeverity value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.severity = value;
        
    }

    /**
     * Default implementation does nothing
     */
    @objid ("d9ecb513-cdba-46de-a706-8cc7d52c6181")
    @Override
    public void postDeleteControls(IRuleControlPoster poster, final MObject deletedElement, final MObject ownerElement) {
        
    }

    /**
     * Default implementation does nothing
     */
    @objid ("c5bfbba9-911c-48f4-b655-fe79ee31dbf3")
    @Override
    public void postCreateControls(IRuleControlPoster poster, final MObject createdElement) {
        
    }

    /**
     * Default implementation does nothing
     */
    @objid ("7defcc0a-7af1-4678-8af6-ea5a7b1ccb53")
    @Override
    public void postUpdateControls(IRuleControlPoster poster, final MObject updatedElement) {
        
    }

    /**
     * Default implementation does nothing
     */
    @objid ("491d0913-970b-4150-8e16-155786f0bb47")
    @Override
    public void postMoveControls(IRuleControlPoster poster, IElementMovedEvent moveEvent) {
        
    }

    @objid ("750e05eb-0718-492f-a3b0-b5807e3abd64")
    @Override
    @Deprecated
    public final void postChildRemovedFromControls(IRuleControlPoster poster, MObject removedElement, MObject oldParent) {
        
    }

}
