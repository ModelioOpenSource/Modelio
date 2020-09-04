/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.infrastructure.dependency;

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
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Dependency} with << related_diagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("fca5b424-314d-4acd-b390-d416296df62e")
public class RelatedDiagram {
    @objid ("f6591675-efac-4b35-b3f0-5abbec756393")
    public static final String STEREOTYPE_NAME = "related_diagram";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("6bed0e71-4e1c-4b03-b889-52181598ce52")
    protected final Dependency elt;

    /**
     * Tells whether a {@link RelatedDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << related_diagram >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("3255d67d-d62c-4651-b7ab-67a8fbc8472f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, RelatedDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << related_diagram >> then instantiate a {@link RelatedDiagram} proxy.
     * 
     * @return a {@link RelatedDiagram} proxy on the created {@link Dependency}.
     */
    @objid ("1a2fea1a-f728-4169-a916-3d2ddccd031d")
    public static RelatedDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, RelatedDiagram.STEREOTYPE_NAME);
        return RelatedDiagram.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link RelatedDiagram} proxy from a {@link Dependency} stereotyped << related_diagram >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link RelatedDiagram} proxy or <i>null</i>.
     */
    @objid ("ab19639a-aa20-46b4-986b-2d9db6fc3d2a")
    public static RelatedDiagram instantiate(Dependency obj) {
        return RelatedDiagram.canInstantiate(obj) ? new RelatedDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link RelatedDiagram} proxy from a {@link Dependency} stereotyped << related_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link RelatedDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("aebc8586-be81-460d-a71f-d1fa6901865f")
    public static RelatedDiagram safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (RelatedDiagram.canInstantiate(obj))
        	return new RelatedDiagram(obj);
        else
        	throw new IllegalArgumentException("RelatedDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("73cb9dd2-0815-4d2e-94a5-dc4f41e49a75")
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
        RelatedDiagram other = (RelatedDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("b4580229-b57f-4a57-9f3a-86c88654bc00")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("90e9ddd4-a3f0-4d0a-8139-1a4e957079b0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("94dc110a-ed1b-4599-a71f-ddfcfa4faa36")
    protected RelatedDiagram(Dependency elt) {
        this.elt = elt;
    }

    @objid ("7c15b3f3-14bd-419f-b399-40c4a720d7a2")
    public static final class MdaTypes {
        @objid ("381fca35-35e8-4ba3-83b9-c5245b3dc33b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a77e2eea-942d-4c43-890a-3fd45be47d67")
        private static Stereotype MDAASSOCDEP;

        @objid ("9261a84b-c363-4977-9876-0d5957a547ae")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6094962f-60dc-4f14-9825-cda6aef83188")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "2961d57b-5120-11de-bbaf-00218648fa3d");
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
