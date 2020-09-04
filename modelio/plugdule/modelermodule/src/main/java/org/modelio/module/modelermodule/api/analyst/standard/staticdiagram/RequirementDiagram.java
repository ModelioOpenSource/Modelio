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
 * Proxy class to handle a {@link StaticDiagram} with << requirement_diagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d48cb628-f2fd-4e33-96f1-a085f4eea318")
public class RequirementDiagram {
    @objid ("65ee9cc2-4caf-450e-9c96-73d9e9fa7332")
    public static final String STEREOTYPE_NAME = "requirement_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("6cfe76a6-173c-40ef-90f0-47ad99e03960")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link RequirementDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << requirement_diagram >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("5115589a-476d-40ee-8d8f-0ef2475cabea")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, RequirementDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << requirement_diagram >> then instantiate a {@link RequirementDiagram} proxy.
     * 
     * @return a {@link RequirementDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("b17af5aa-87af-4290-b68e-806202dcd1f0")
    public static RequirementDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, RequirementDiagram.STEREOTYPE_NAME);
        return RequirementDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link RequirementDiagram} proxy from a {@link StaticDiagram} stereotyped << requirement_diagram >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link RequirementDiagram} proxy or <i>null</i>.
     */
    @objid ("3b70ae9c-8ece-4d99-bf00-716a4d6adebc")
    public static RequirementDiagram instantiate(StaticDiagram obj) {
        return RequirementDiagram.canInstantiate(obj) ? new RequirementDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link RequirementDiagram} proxy from a {@link StaticDiagram} stereotyped << requirement_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StaticDiagram}
     * @return a {@link RequirementDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("89a0ef8e-ca16-492b-8ea9-a08e60d5e66d")
    public static RequirementDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (RequirementDiagram.canInstantiate(obj))
        	return new RequirementDiagram(obj);
        else
        	throw new IllegalArgumentException("RequirementDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c1e72a2c-6464-4f56-9881-a7ece155bbdc")
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
        RequirementDiagram other = (RequirementDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StaticDiagram}. 
     * @return the StaticDiagram represented by this proxy, never null.
     */
    @objid ("6b88eea2-d6fc-4d2a-97c1-2afe9b9d3312")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("870fb5aa-8fdb-4747-9699-f4c8aa33a8ce")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c38b9700-295b-4aab-a8ee-cbadf03573cd")
    protected RequirementDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("857134ec-82ed-42fe-a6bb-b747f67e61ff")
    public static final class MdaTypes {
        @objid ("692b65fa-10d5-4219-8e98-7c5d32ac41c2")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("539f82d5-f354-4617-8ca3-4e9c661f8b69")
        private static Stereotype MDAASSOCDEP;

        @objid ("07c0610a-0094-4ce4-b2f2-289c3a694a1e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("fc3baaf5-51af-4827-830f-a8759b1de767")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0bfd-0000-000000000000");
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
