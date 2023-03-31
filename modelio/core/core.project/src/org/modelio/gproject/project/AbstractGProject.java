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
package org.modelio.gproject.project;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.GProblem;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.core.IGProject;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("30461838-7614-4c90-818e-342c4526976b")
public abstract class AbstractGProject implements IGProject {
    @objid ("9d40fc55-122c-4e71-b7d3-14a829f16047")
    private static List<IGProject> allProjects = new ArrayList<>();

    /**
     * The session when the project is opened The session when the project is opened
     */
    @objid ("2522fb11-1650-457f-a60b-ad42af229df9")
    protected CoreSession session;

    @objid ("e8f21f2e-632e-4b17-9cf1-1d5102f29ff6")
    protected final List<GProblem> problems = new ArrayList<>();

    @objid ("8c8f8cdf-9247-4019-b210-b3adb236822f")
    @Override
    public ICoreSession getSession() {
        return this.session;
    }

    @objid ("c8488630-b715-4abb-b4cd-c19f5a2019ae")
    public  AbstractGProject() {
        allProjects.add(this);
    }

    @objid ("13d122d4-3277-41db-bc42-c647fadce346")
    @Override
    public void close() {
        if (this.session != null) {
            this.session.close();
            this.session = null;
        }
        allProjects.remove(this);
        
        // Clear problems
        this.problems.clear();
        
    }

    @objid ("ff3d12b1-81a1-48a7-bc14-478be3373435")
    @Override
    public List<GProblem> getProblems() {
        return this.problems;
    }

    /**
     * Get the GProject owning the given core session.
     * <p>
     * @return <code>null</code> if the core session is not managed by a GProject.
     */
    @objid ("e443a9be-9408-43b1-b325-d2f8c9ed75b2")
    public static IGProject getProject(ICoreSession session) {
        for (IGProject p : AbstractGProject.allProjects) {
            if (p.getSession() == session) {
                return p;
            }
        }
        return null;
    }

    @objid ("636327af-82c5-4cef-bb49-bb33322a59b1")
    public static IGProject getProject(MObject mObj) {
        return getProject(CoreSession.getSession(mObj));
    }

    /**
     * Get the model fragment in this project owning the given model object.
     * <p>
     * Returns <code>null</code> if the model object is not managed by a fragment.
     * @param obj a model object.
     * @return its fragment or <code>null</code>.
     */
    @objid ("00269abc-7c41-400d-906c-774046758ab8")
    @Override
    public IGModelFragment getFragment(MObject obj) {
        if (this.session != null) {
            IRepository repo = this.session.getRepositorySupport().getRepository(obj);
            for (IGModelFragment f : getParts(IGModelFragment.class)) {
                if (f.getRepository() == repo) {
                    return f;
                }
            }
        }
        return null;
    }

}
