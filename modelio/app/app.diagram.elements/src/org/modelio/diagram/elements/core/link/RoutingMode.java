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
package org.modelio.diagram.elements.core.link;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Represents the routing mode of the link.
 * <p>
 * Aggregates a boolean rake mode and a {@link ConnectionRouterId} .
 * If {@link #rake} is true the rake router must be used and {@link #routingStyle} is useless.
 * 
 * @author cmarin
 */
@objid ("8020be85-1dec-11e2-8cad-001ec947c8cc")
public class RoutingMode {
    /**
     * Rake mode.
     */
    @objid ("8020be88-1dec-11e2-8cad-001ec947c8cc")
    public boolean rake;

    /**
     * Connection router identifier.
     */
    @objid ("8e6298a4-1e83-11e2-8cad-001ec947c8cc")
    public ConnectionRouterId routingStyle;

    /**
     * Default constructor: no rake and no routing style.
     */
    @objid ("802320ac-1dec-11e2-8cad-001ec947c8cc")
    public  RoutingMode() {
        this.rake = false;
        this.routingStyle = null;
        
    }

    /**
     * Constructor that reads a {@link IGmPath}
     * @param path the path to extract informations from
     */
    @objid ("802320ae-1dec-11e2-8cad-001ec947c8cc")
    public  RoutingMode(final IGmPath path) {
        this.routingStyle = path.getRouterKind();
        this.rake = path.getSourceRake() != null || path.getTargetRake() != null;
        
    }

    @objid ("802320b2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.rake ? 1231 : 1237);
        result = prime * result + (this.routingStyle == null ? 0 : this.routingStyle.hashCode());
        return result;
    }

    @objid ("802320b7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        RoutingMode other = (RoutingMode) obj;
        if (this.rake != other.rake) {
            return false;
        }
        if (this.routingStyle == null) {
            if (other.routingStyle != null) {
                return false;
            }
        } else if (this.routingStyle != other.routingStyle) {
            return false;
        }
        return true;
    }

    @objid ("d81d1379-36da-403e-bb7e-3fecc1c948f5")
    @Override
    public String toString() {
        if (this.rake) {
            return getClass().getSimpleName()+"[RAKE ("+this.routingStyle+")]";
        } else {
            return getClass().getSimpleName()+"["+this.routingStyle+"]";
        }
        
    }

}
