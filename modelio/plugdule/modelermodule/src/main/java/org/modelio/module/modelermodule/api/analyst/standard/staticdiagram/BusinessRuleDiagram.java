/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.analyst.standard.staticdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.diagrams.StaticDiagram;
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
 * Proxy class to handle a {@link StaticDiagram} with << business_rule_diagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("b8e1e546-cae7-4787-a2ae-463c7c107079")
public class BusinessRuleDiagram {
    @objid ("5de87a75-6b1a-4929-b76a-e6e7a8ec2041")
    public static final String STEREOTYPE_NAME = "business_rule_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("8dce4651-6637-415b-bff6-37186025f57e")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link BusinessRuleDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << business_rule_diagram >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("818a87d7-f3d2-4e31-b6cf-6006a3bd494d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, BusinessRuleDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << business_rule_diagram >> then instantiate a {@link BusinessRuleDiagram} proxy.
     * 
     * @return a {@link BusinessRuleDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("9ce2c7a7-dfa3-4dda-9dd1-188004bebe8b")
    public static BusinessRuleDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, BusinessRuleDiagram.STEREOTYPE_NAME);
        return BusinessRuleDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link BusinessRuleDiagram} proxy from a {@link StaticDiagram} stereotyped << business_rule_diagram >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link BusinessRuleDiagram} proxy or <i>null</i>.
     */
    @objid ("79dce121-a28e-4833-8c89-f047ea55ea6a")
    public static BusinessRuleDiagram instantiate(StaticDiagram obj) {
        return BusinessRuleDiagram.canInstantiate(obj) ? new BusinessRuleDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link BusinessRuleDiagram} proxy from a {@link StaticDiagram} stereotyped << business_rule_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StaticDiagram}
     * @return a {@link BusinessRuleDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("04b45383-e6fb-4d46-9010-76b328cf0ca4")
    public static BusinessRuleDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (BusinessRuleDiagram.canInstantiate(obj))
        	return new BusinessRuleDiagram(obj);
        else
        	throw new IllegalArgumentException("BusinessRuleDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ed43d876-18f3-4823-b156-5dbd2001e0e9")
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
        BusinessRuleDiagram other = (BusinessRuleDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StaticDiagram}. 
     * @return the StaticDiagram represented by this proxy, never null.
     */
    @objid ("238f8195-e88e-4852-bc12-2be0d2edb2a5")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("cee8972f-5240-4d88-bf28-f5c6983f956f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e59d679c-79c8-4b0e-966a-020fd38e71f6")
    protected BusinessRuleDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("cb89aa15-04f6-45ea-9d10-613b4dc93dc4")
    public static final class MdaTypes {
        @objid ("893aefe3-aef0-432f-921a-aec1474b8384")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("50b6006a-e6b9-4432-8a26-664aa7a19a22")
        private static Stereotype MDAASSOCDEP;

        @objid ("d0c39186-76ae-466c-a5c8-56db58f7abcb")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("963b829d-6bb7-4647-aa0e-4a904284015e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0aca-0000-000000000000");
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
