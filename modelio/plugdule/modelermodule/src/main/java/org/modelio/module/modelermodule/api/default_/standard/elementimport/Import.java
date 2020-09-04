/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.elementimport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link ElementImport} with << import >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("450c1690-12d6-42fc-958f-edcb65fd95bc")
public class Import {
    @objid ("de1ec8d4-8714-41fd-b254-debd13c69eaf")
    public static final String STEREOTYPE_NAME = "import";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("aabe4c9f-18a5-4fcf-83f8-3efc5c637e5e")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Import proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << import >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("604fdedb-62b1-4969-a381-53c432b47e50")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Import.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << import >> then instantiate a {@link Import} proxy.
     * 
     * @return a {@link Import} proxy on the created {@link ElementImport}.
     */
    @objid ("c820593a-6efe-49a4-ae25-cae61694c713")
    public static Import create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Import.STEREOTYPE_NAME);
        return Import.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Import} proxy from a {@link ElementImport} stereotyped << import >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Import} proxy or <i>null</i>.
     */
    @objid ("894bf2c7-0d04-4547-af8f-4cda28c33b2b")
    public static Import instantiate(ElementImport obj) {
        return Import.canInstantiate(obj) ? new Import(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Import} proxy from a {@link ElementImport} stereotyped << import >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ElementImport}
     * @return a {@link Import} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("8f075ac2-96cf-440b-b703-ac7f32bb17d7")
    public static Import safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Import.canInstantiate(obj))
        	return new Import(obj);
        else
        	throw new IllegalArgumentException("Import: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("0d66ce3a-5b66-413b-8f59-c591cac2ff03")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Import other = (Import) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ElementImport}. 
     * @return the ElementImport represented by this proxy, never null.
     */
    @objid ("e2b50204-d736-4ec8-8205-9bd2b41f0cbd")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("0fe547a5-05da-4db4-b9ae-c69b612df18a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b44309fa-b2b2-4c99-8f0c-5556fbc270b8")
    protected Import(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("abc33775-3a58-4320-bd1b-1809fc8c7925")
    public static final class MdaTypes {
        @objid ("6e8d7ae1-7910-4c22-b9fc-cc1617b11417")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("69d7065e-c8bc-4a01-b630-d2ad8f948e5c")
        private static Stereotype MDAASSOCDEP;

        @objid ("14bf0e1a-9d2b-41a3-9593-02898164628d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("03d4ef51-7817-496c-b600-ed2762f0aa90")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01ce-0000-000000000000");
            MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
        }


	static {
		if(ModelerModuleModule.getInstance() != null) {
			init(ModelerModuleModule.getInstance().getModuleContext());
		}
	}
    }

}
