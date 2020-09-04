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

package org.modelio.metamodel.impl.mmextensions.standard.populator;

import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.model.api.IRepositoryContentInitializer;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.impl.GenericFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Initialize new fragments content.
 * <p>
 * Creates the Project, Analyst roots, diagram roots and the root package.
 */
@objid ("a5ae7caf-bb73-4e78-8720-9ce89ce358fb")
public class StandardPopulator implements IRepositoryContentInitializer {
    /**
     * Populate the given repository using the given session.
     * 
     * @param fragmentName the fragment name
     * @param s the session to use
     * @param repository the repository to populate.
     * @return the created objects.
     */
    @objid ("8ff06823-60c1-42a3-b3f3-80ff45afb88e")
    @Override
    public Collection<MObject> populate(String fragmentName, ICoreSession s, IRepository repository) {
        Collection<MObject> ret = new ArrayList<>();
        GenericFactory gf = s.getModel().getGenericFactory();
        
        // Create project and root package
        Project proj = gf.create(Project.class, repository);
        Package root = gf.create(Package.class, repository);
        proj.getModel().add(root);
        
        proj.setName(fragmentName);
        root.setName(fragmentName.toLowerCase());
        
        ret.add(proj);
        ret.add(root);
        
        // Create analyst project
        DiagramSet dgRootSet = gf.create(DiagramSet.class, repository);
        proj.setDiagramRoot(dgRootSet);
        
        ret.add(dgRootSet);
        
        dgRootSet.setName(fragmentName);
        return ret;
    }

}
