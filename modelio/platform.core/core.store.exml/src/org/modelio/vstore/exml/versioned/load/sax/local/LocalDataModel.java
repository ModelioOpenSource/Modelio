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

package org.modelio.vstore.exml.versioned.load.sax.local;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vstore.exml.common.ILoadHelper;
import org.modelio.vstore.exml.common.index.IndexException;
import org.modelio.vstore.exml.common.model.ExmlTags;
import org.modelio.vstore.exml.common.model.IllegalReferenceException;
import org.modelio.vstore.exml.common.model.ObjId;
import org.modelio.vstore.exml.common.model.ObjIdName;
import org.modelio.vstore.exml.local.loader.sax.IDependencyContentHook.Content;
import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;

/**
 * Local EXML file data model.
 */
@objid ("b5c32bf3-3fbb-11e2-87cb-001ec947ccaf")
final class LocalDataModel implements ExmlTags {
    /**
     * Read file format version
     */
    @objid ("b5c32b93-3fbb-11e2-87cb-001ec947ccaf")
    private int version;

    @objid ("b5c32bf7-3fbb-11e2-87cb-001ec947ccaf")
     ILoadHelper loadHelper;

    @objid ("b5c32bf9-3fbb-11e2-87cb-001ec947ccaf")
     IModelLoader modelLoader;

    @objid ("b5c32bfb-3fbb-11e2-87cb-001ec947ccaf")
    private ObjectNode currentNode;

    @objid ("b5c32bfc-3fbb-11e2-87cb-001ec947ccaf")
    private Collection<ObjectNode> nodes;

    @objid ("b5c32bff-3fbb-11e2-87cb-001ec947ccaf")
    private Locator locator;

    @objid ("b5c32c0e-3fbb-11e2-87cb-001ec947ccaf")
     static final List<SmObjectImpl> EMPTY_DEP = Collections.emptyList();

    @objid ("ddd99329-407a-11e2-87cb-001ec947ccaf")
    private static final Collection<ObjectNode> EMPTY_NODES = Collections.emptyList();

    @objid ("b5c58dbd-3fbb-11e2-87cb-001ec947ccaf")
    private DepNode currentDepNode = null;

    /**
     * initialize the loader
     * @param loadHelper a load helper
     */
    @objid ("b5c32c01-3fbb-11e2-87cb-001ec947ccaf")
    public LocalDataModel(ILoadHelper loadHelper) {
        this.loadHelper = loadHelper;
        this.modelLoader = null;
        this.nodes = EMPTY_NODES;
    }

    /**
     * Reset the data model to its initial state.
     */
    @objid ("b5c58df3-3fbb-11e2-87cb-001ec947ccaf")
    public void reset() {
        this.locator = null;
        this.nodes.clear();
        this.currentNode = null;
        this.currentDepNode = null;
    }

    @objid ("b5c32b8b-3fbb-11e2-87cb-001ec947ccaf")
    void addDepId(final ObjIdName refid) throws DuplicateObjectException, IllegalReferenceException, IndexException {
        if (refid != null) {
            SmObjectImpl obj = this.loadHelper.getLoadedObject(refid.toObjId());
            if (obj == null) {
                obj = this.loadHelper.getRefObject(this.modelLoader,refid);
            }
        
            this.currentDepNode.add (obj);
        }
    }

    @objid ("b5c58ddd-3fbb-11e2-87cb-001ec947ccaf")
    Locator getLocator() {
        return this.locator;
    }

    @objid ("b5c58dfa-3fbb-11e2-87cb-001ec947ccaf")
    int getVersion() {
        return this.version;
    }

    @objid ("b5c32b80-3fbb-11e2-87cb-001ec947ccaf")
    void myassert(boolean b) throws AssertionError {
        if (! b) {
            final SAXParseException cause = new SAXParseException("assertion failed", getLocator());
            throw new AssertionError(cause.getMessage(), cause);
        }
    }

    @objid ("b5c32b83-3fbb-11e2-87cb-001ec947ccaf")
    void myassert(boolean b, String msg) {
        if (! b) {
            final SAXParseException cause = new SAXParseException(msg, getLocator());
            throw new AssertionError(cause.getMessage(), cause);
        }
    }

    @objid ("b5c58dd8-3fbb-11e2-87cb-001ec947ccaf")
    final void beginObject(ObjIdName id) {
        if (this.nodes == EMPTY_NODES) {
            this.nodes = new ArrayList<>(5);
        }
        
        this.currentNode = new ObjectNode(id.toObjId());
        this.nodes.add(this.currentNode);
    }

    /**
     * Initialize the document locator.
     * @param aLocator a SAX locator.
     */
    @objid ("b5c58deb-3fbb-11e2-87cb-001ec947ccaf")
    void setDocumentLocator(Locator aLocator) {
        this.locator = aLocator;
    }

    /**
     * initialize the model loader.
     * @param modelLoader a model loader.
     */
    @objid ("b5c58def-3fbb-11e2-87cb-001ec947ccaf")
    public void setModelLoader(IModelLoader modelLoader) {
        this.modelLoader = modelLoader;
    }

    /**
     * Set the file format version.
     * @param v the file format version.
     */
    @objid ("b5c58df6-3fbb-11e2-87cb-001ec947ccaf")
    void setVersion(int v) {
        this.version = v;
    }

    @objid ("dddbf547-407a-11e2-87cb-001ec947ccaf")
    void beginDependency(String depName) {
        this.currentDepNode = this.currentNode.beginDependency(depName);
    }

    /**
     * Get the local content of a model object dependency
     * @param obj a model object
     * @param dep a dependency
     * @return the dependency content or <code>null</code> if none.
     */
    @objid ("dddbf54c-407a-11e2-87cb-001ec947ccaf")
    public List<SmObjectImpl> getDependencyContent(SmObjectImpl obj, SmDependency dep) {
        for (ObjectNode  objNode: this.nodes) {
            if (objNode.is(obj.getUuid())) {
                for (DepNode depNode : objNode.getDeps()) {
                    if (depNode.dep == dep) {
                        return depNode.getContent();
                    }
                }
                return null;
            }
        }
        return null;
    }

    /**
     * Get the dependencies for which there is a local content.
     * @param obj a model object
     * @return all dependencies with a local content.
     */
    @objid ("5be9b0f2-10c7-4ad1-b80b-8449d436f12d")
    public Collection<? extends Content> getDependencyContent(SmObjectImpl obj) {
        for (ObjectNode  objNode: this.nodes) {
            if (objNode.is(obj.getUuid())) {
                return objNode.getDeps();
            }
        }
        return Collections.emptyList();
    }

    @objid ("41ffedab-5171-44ff-b116-6d284ffa14d3")
    public SmClass getSmClass(String xclassof) {
        return this.loadHelper.getSmClass(xclassof);
    }

    @objid ("b5c32c12-3fbb-11e2-87cb-001ec947ccaf")
    static final class ObjectNode {
        @objid ("ddd9930d-407a-11e2-87cb-001ec947ccaf")
        private Collection<DepNode> deps = new ArrayList<>();

        @objid ("ddd99310-407a-11e2-87cb-001ec947ccaf")
        private ObjId id;

        @objid ("b5c58dcc-3fbb-11e2-87cb-001ec947ccaf")
        DepNode beginDependency(String depName) {
            final SmDependency dep = this.id.classof.getDependencyDef(depName);
            
            DepNode newDepNode = new DepNode(dep);
            this.deps.add(newDepNode);
            return newDepNode;
        }

        @objid ("b5c32b76-3fbb-11e2-87cb-001ec947ccaf")
        @Override
        public String toString() {
            return this.id + " ObjectNode, with " + this.deps.size()+" dependencies";
        }

        @objid ("ddd99311-407a-11e2-87cb-001ec947ccaf")
        public ObjectNode(ObjId objid) {
            this.id = objid;
        }

        @objid ("ddd99314-407a-11e2-87cb-001ec947ccaf")
        Collection<DepNode> getDeps() {
            return this.deps;
        }

        @objid ("ddd9931c-407a-11e2-87cb-001ec947ccaf")
        public Collection<SmObjectImpl> getDependencyContent(SmDependency dep) {
            for (DepNode  d: this.deps) {
                if (d.dep == dep) {
                    return d.getContent();
                }
            }
            return null;
        }

        @objid ("ddd99324-407a-11e2-87cb-001ec947ccaf")
        boolean is(String uuid) {
            return this.id.id.equals(uuid);
        }

    }

    @objid ("ddd992fa-407a-11e2-87cb-001ec947ccaf")
    static final class DepNode implements Content {
        @objid ("ddd992fc-407a-11e2-87cb-001ec947ccaf")
         List<SmObjectImpl> content;

        @objid ("ddd992ff-407a-11e2-87cb-001ec947ccaf")
         SmDependency dep;

        @objid ("ddd99300-407a-11e2-87cb-001ec947ccaf")
        public DepNode(SmDependency adep) {
            this.dep = adep;
            this.content = new ArrayList<>();
        }

        @objid ("ddd99303-407a-11e2-87cb-001ec947ccaf")
        void add(SmObjectImpl ref) {
            this.content.add(ref);
        }

        @objid ("ddd99306-407a-11e2-87cb-001ec947ccaf")
        @Override
        public List<SmObjectImpl> getContent() {
            return this.content;
        }

        @objid ("6113049f-281f-40cf-8058-ef62b46cd5bf")
        @Override
        public SmDependency getDep() {
            return this.dep;
        }

    }

}
