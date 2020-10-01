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

package org.modelio.diagram.editor.plugin;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("6670d463-33f7-11e2-95fe-001ec947c8cc")
public interface IDiagramConfigurerRegistry {
    /**
     * Registers a {@link IDiagramConfigurer} for the given metaclass and stereotype.
     * 
     * @param metaclassName the name of the diagram metaclass the given IDiagramConfigurer is bound to. Must not be <code>null</code> nor
     * empty.
     * @param stereotype the stereotype the given IDiagramConfigurer is bound to. May be <code>null</code> or empty.
     * @param configurer the IDiagramConfigurer to register.
     */
    @objid ("6670d464-33f7-11e2-95fe-001ec947c8cc")
    void registerDiagramConfigurer(final String metaclassName, final String stereotype, final IDiagramConfigurer configurer);

    /**
     * Unregisters a {@link IDiagramConfigurer} for the given metaclass and stereotype.
     * 
     * @param metaclassName the name of the diagram metaclass the given IDiagramConfigurer is bound to. Must not be <code>null</code> nor
     * empty.
     * @param stereotype the stereotype the given IDiagramConfigurer is bound to. May be <code>null</code> or empty.
     * @param configurer the IDiagramConfigurer to unregister.
     */
    @objid ("6670d46c-33f7-11e2-95fe-001ec947c8cc")
    void unregisterDiagramConfigurer(final String metaclassName, final String stereotype, final IDiagramConfigurer configurer);

    /**
     * Returns the {@link IDiagramConfigurer}s for the given metaclass and stereotypes if any, an empty list otherwise.
     * 
     * @param metaclassName the name of the diagram metaclass. Must not be <code>null</code> nor empty.
     * @param stereotypes the list of stereotypes. May be <code>null</code> or empty.
     */
    @objid ("6670d474-33f7-11e2-95fe-001ec947c8cc")
    List<IDiagramConfigurer> getConfigurers(final String metaclassName, final List<String> stereotypes);

    /**
     * Returns the {@link IDiagramConfigurer}s for the given metaclass.
     * 
     * @param metaclassName the name of the diagram metaclass. Must not be <code>null</code> nor empty.
     */
    @objid ("667336a1-33f7-11e2-95fe-001ec947c8cc")
    IDiagramConfigurer getConfigurer(final String metaclassName);

}
