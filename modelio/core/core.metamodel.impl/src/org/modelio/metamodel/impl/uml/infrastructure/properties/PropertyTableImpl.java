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
/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.ref.SoftReference;
import java.util.Properties;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ElementImpl;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00691798-ec87-1098-b22e-001ec947cd2a")
public class PropertyTableImpl extends ElementImpl implements PropertyTable {
    @objid ("45603d41-d582-4fc0-83ff-a25543c4c6f2")
    private int tableHash;

    @objid ("c8640425-848f-4d88-a567-c5c74b734412")
    private SoftReference<Properties> table;

    @objid ("71fabe31-282d-11e2-bf07-001ec947ccaf")
    @Override
    public String getProperty(String key) {
        Properties props = getSyncProperties();
        return props.getProperty(key);
    }

    @objid ("71fabe37-282d-11e2-bf07-001ec947ccaf")
    @Override
    public final Properties toProperties() throws IllegalStateException {
        Properties props = new Properties();
        props.putAll(getSyncProperties());
        return props;
    }

    @objid ("71fabe3c-282d-11e2-bf07-001ec947ccaf")
    @Override
    public final void setContent(Properties newContent) {
        StringWriter sw = new StringWriter();
        try {
            newContent.store(sw, null);
            final String s = sw.toString();
            setContent(s);
        
            // Update the cache
            this.table = new SoftReference<>(newContent);
            this.tableHash = s.hashCode();
        } catch (IOException e) {
            throw new Error(e); // cannot happen
        }
        
    }

    @objid ("71fabe40-282d-11e2-bf07-001ec947ccaf")
    @Override
    public void setProperty(String key, String value) {
        Properties props = getSyncProperties();
        
        if (value == null) {
            props.remove(key);
        } else {
            props.setProperty(key, value);
        }
        
        setContent(props);
        
    }

    /**
     * Update the cached Properties from the string content.
     * @return the new cached Properties.
     */
    @objid ("b5b0b9f3-8d2c-44a1-8173-3c97f0a44fe1")
    private Properties computeTable() {
        Properties props = new Properties();
        try {
            final String strContent = getContent();
            props.load(new StringReader(strContent));
            this.table = new SoftReference<>(props);
            this.tableHash = strContent.hashCode();
        } catch (IOException e) {
            throw new IllegalStateException("The 'Content' attribute has not the java.util.Properties format:"
                    + e.getLocalizedMessage(), e);
        }
        return props;
    }

    /**
     * Get the internal property table.
     * <p>
     * The internal property table reflects the string content at the moment it is computed.
     * @return the internal property table.
     */
    @objid ("0ab19bd3-e3e4-41f5-be52-5cee16a1623e")
    private Properties getSyncProperties() {
        final Properties syncTable = isTableSync();
        
        if (syncTable == null)
            return computeTable();
        else
            return syncTable;
        
    }

    /**
     * Return the cached properties if the Properties is up to date with the string content.
     * @return the cached property table or <code>null</code>.
     */
    @objid ("07ffcaec-5a48-4ecb-ba9d-767191ca6ac7")
    private Properties isTableSync() {
        if (this.table == null)
            return null;
        
        Properties props = this.table.get();
        if (props == null)
            return null;
        
        if (this.tableHash != getContent().hashCode())
            return null;
        return props;
    }

    @objid ("e0059d8c-fcb4-4a5e-970b-651bf211c3c1")
    @Override
    public String getName() {
        return (String) getAttVal(((PropertyTableSmClass)getClassOf()).getNameAtt());
    }

    @objid ("8082709c-33f1-4715-a41e-b505395f70a1")
    @Override
    public void setName(String value) {
        setAttVal(((PropertyTableSmClass)getClassOf()).getNameAtt(), value);
    }

    @objid ("819f7012-a663-41d6-aae3-d591e5cc7f05")
    @Override
    public String getContent() {
        return (String) getAttVal(((PropertyTableSmClass)getClassOf()).getContentAtt());
    }

    @objid ("1fe82863-c41e-4e3a-ab7e-ec59476470b5")
    @Override
    public void setContent(String value) {
        setAttVal(((PropertyTableSmClass)getClassOf()).getContentAtt(), value);
    }

    @objid ("f2d6ff07-bda8-4f50-80a2-3bbf31bf81b3")
    @Override
    public MatrixValueDefinition getOwnerValDef() {
        Object obj = getDepVal(((PropertyTableSmClass)getClassOf()).getOwnerValDefDep());
        return (obj instanceof MatrixValueDefinition)? (MatrixValueDefinition)obj : null;
    }

    @objid ("470f8520-2c3f-44ed-936d-247cb2969c65")
    @Override
    public void setOwnerValDef(MatrixValueDefinition value) {
        appendDepVal(((PropertyTableSmClass)getClassOf()).getOwnerValDefDep(), (SmObjectImpl)value);
    }

    @objid ("cf16743c-4bc2-4bf4-8756-54ba60f7c990")
    @Override
    public QueryDefinition getOwnerQuery() {
        Object obj = getDepVal(((PropertyTableSmClass)getClassOf()).getOwnerQueryDep());
        return (obj instanceof QueryDefinition)? (QueryDefinition)obj : null;
    }

    @objid ("32ebcd17-f382-4777-a135-66bfb28d5827")
    @Override
    public void setOwnerQuery(QueryDefinition value) {
        appendDepVal(((PropertyTableSmClass)getClassOf()).getOwnerQueryDep(), (SmObjectImpl)value);
    }

    @objid ("8d4b4516-94ab-40ac-91c2-3cb0c5aa29e4")
    @Override
    public ModelElement getOwner() {
        Object obj = getDepVal(((PropertyTableSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("d6c0914f-b0a0-4298-93cc-0321b72e57d7")
    @Override
    public void setOwner(ModelElement value) {
        appendDepVal(((PropertyTableSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("0cc65dd1-1db9-4167-a484-4ec8b4a549f0")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // OwnerValDef
        obj = (SmObjectImpl)this.getDepVal(((PropertyTableSmClass)getClassOf()).getOwnerValDefDep());
        if (obj != null)
          return obj;
        // OwnerQuery
        obj = (SmObjectImpl)this.getDepVal(((PropertyTableSmClass)getClassOf()).getOwnerQueryDep());
        if (obj != null)
          return obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((PropertyTableSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("a5f7a8eb-dfc2-4d28-8603-e3ebd216d031")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // OwnerValDef
        dep = ((PropertyTableSmClass)getClassOf()).getOwnerValDefDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // OwnerQuery
        dep = ((PropertyTableSmClass)getClassOf()).getOwnerQueryDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // Owner
        dep = ((PropertyTableSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        return super.getCompositionRelation();
    }

    @objid ("c04d6635-d000-4a88-8dd6-a936423e09e6")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitPropertyTable(this);
    }

}
