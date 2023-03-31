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
package org.modelio.metamodel.impl.control;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Error codes for real time checkers.
 * 
 * RANGE: 100-199
 * 
 * @author phv
 */
@objid ("0012e832-4d7d-1036-812a-001ec947cd2a")
public interface ControlErrorCodes {
    @objid ("001302fe-4d7d-1036-812a-001ec947cd2a")
    public static final int NO_ERROR = 0;

    @objid ("00131816-4d7d-1036-812a-001ec947cd2a")
    public static final int ATTRIBUTE_INVALID_TYPE = 100;

    @objid ("00292d90-58fa-1036-812a-001ec947cd2a")
    public static final int BINDABLEINSTANCE_INVALID_INTERNALOWNER = 101;

    @objid ("00293ff6-58fa-1036-812a-001ec947cd2a")
    public static final int COLLABORATIONUSE_INVALID_NREPRESENTED = 102;

    @objid ("002952a2-58fa-1036-812a-001ec947cd2a")
    public static final int CONNECTOREND_INVALID_REPRESENTEDFEATURE = 103;

    @objid ("00296580-58fa-1036-812a-001ec947cd2a")
    public static final int BINDABLEINSTANCE_INVALIDOWNER = 104;

    @objid ("0029789a-58fa-1036-812a-001ec947cd2a")
    public static final int INTERFACEREALIZATION_INVALID_IMPLEMENTER = 105;

    @objid ("00298c36-58fa-1036-812a-001ec947cd2a")
    public static final int ACTOR_INVALID_OWNER = 106;

    @objid ("0029a00e-58fa-1036-812a-001ec947cd2a")
    public static final int ARTIFACT_INVALID_OWNER = 107;

    @objid ("0029b422-58fa-1036-812a-001ec947cd2a")
    public static final int CLASS_INVALID_OWNER = 108;

    @objid ("0029c872-58fa-1036-812a-001ec947cd2a")
    public static final int COLLABORATION_INVALID_OWNER = 109;

    @objid ("0029dcd6-58fa-1036-812a-001ec947cd2a")
    public static final int COMPONENT_INVALID_OWNER = 110;

    @objid ("0029f176-58fa-1036-812a-001ec947cd2a")
    public static final int DATATYPE_INVALID_OWNER = 111;

    @objid ("002a067a-58fa-1036-812a-001ec947cd2a")
    public static final int ENUMERATION_INVALID_OWNER = 112;

    @objid ("002a1bc4-58fa-1036-812a-001ec947cd2a")
    public static final int INTERFACE_INVALID_OWNER = 113;

    @objid ("002a3154-58fa-1036-812a-001ec947cd2a")
    public static final int NODE_INVALID_OWNER = 114;

    @objid ("002a46ee-58fa-1036-812a-001ec947cd2a")
    public static final int PACKAGE_INVALID_OWNER = 115;

    @objid ("002a5ce2-58fa-1036-812a-001ec947cd2a")
    public static final int SIGNAL_INVALID_OWNER = 116;

    @objid ("002a72ea-58fa-1036-812a-001ec947cd2a")
    public static final int TEMPLATEPARAMETER_INVALID_OWNER = 117;

    @objid ("002a894c-58fa-1036-812a-001ec947cd2a")
    public static final int MODELTREE_SELF_OWNER = 118;

    @objid ("002a9fea-58fa-1036-812a-001ec947cd2a")
    public static final int PARAMETER_INVALID_TYPE = 119;

    @objid ("002ab6c4-58fa-1036-812a-001ec947cd2a")
    public static final int RAISEDEXCEPTION_INVALID_THROWNTYPE = 120;

    @objid ("002ace2a-58fa-1036-812a-001ec947cd2a")
    public static final int TEMPLATEBINDING_INVALID_BOUNDELEMENT = 121;

    @objid ("002ae5c2-58fa-1036-812a-001ec947cd2a")
    public static final int TEMPLATEPARAMETER_INVALID_PARAMETRIZED = 122;

    @objid ("02f9ad64-f027-11e1-8bdc-002564c97630")
    public static final int STEREOTYPE_INCOMPATIBLE_METACLASS = 123;

    @objid ("02f9ad66-f027-11e1-8bdc-002564c97630")
    public static final int TAGGEDVALUE_INCOMPATIBLE_METACLASS = 124;

    @objid ("02fb3404-f027-11e1-8bdc-002564c97630")
    public static final int NOTE_INCOMPATIBLE_METACLASS = 125;

    @objid ("02fb3406-f027-11e1-8bdc-002564c97630")
    public static final int EXTERNDOCUMENT_INCOMPATIBLE_METACLASS = 126;
}

