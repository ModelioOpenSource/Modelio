/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition;

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
 * Proxy class to handle a {@link PropertyTableDefinition} with << kpi_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("94e0054a-b4c4-4633-b4ad-1f5362743248")
public class KpiPropertyset {
    @objid ("f2c3cfe7-f834-4136-a37c-d45edd2a2936")
    public static final String STEREOTYPE_NAME = "kpi_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("4cf939a3-6f77-4f73-b5a9-fe65c318d8ac")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link KpiPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << kpi_propertyset >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("a4a2febe-a0a0-4122-9009-8a3c9e6bb4ca")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, KpiPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << kpi_propertyset >> then instantiate a {@link KpiPropertyset} proxy.
     * 
     * @return a {@link KpiPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("98e1a882-17fd-4cbc-97a4-3ba9586c7701")
    public static KpiPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("PropertyTableDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, KpiPropertyset.STEREOTYPE_NAME);
        return KpiPropertyset.instantiate((PropertyTableDefinition)e);
    }

    /**
     * Tries to instantiate a {@link KpiPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << kpi_propertyset >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a PropertyTableDefinition
     * @return a {@link KpiPropertyset} proxy or <i>null</i>.
     */
    @objid ("7c8f271f-f3c9-4fff-8b71-f57010aff791")
    public static KpiPropertyset instantiate(PropertyTableDefinition obj) {
        return KpiPropertyset.canInstantiate(obj) ? new KpiPropertyset(obj) : null;
    }

    /**
     * Tries to instantiate a {@link KpiPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << kpi_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link PropertyTableDefinition}
     * @return a {@link KpiPropertyset} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("5cea2237-615b-47fd-91b1-f50d3667d9bf")
    public static KpiPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (KpiPropertyset.canInstantiate(obj))
        	return new KpiPropertyset(obj);
        else
        	throw new IllegalArgumentException("KpiPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("86586701-86b2-4fd9-928d-ace45b03fe3f")
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
        KpiPropertyset other = (KpiPropertyset) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link PropertyTableDefinition}. 
     * @return the PropertyTableDefinition represented by this proxy, never null.
     */
    @objid ("3505aaa4-c41a-4591-8ffa-821ad6eb5376")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("e8774c3d-e63a-4982-af5c-13be344156fb")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("7c281819-81f4-4708-84f2-6cd7b766a175")
    protected KpiPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("5a1258eb-5565-4ee1-b232-31e1a693018d")
    public static final class MdaTypes {
        @objid ("48db9369-a90f-4b65-914e-858a9c62b2f2")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a665e9b7-39ef-42c7-b4d5-ceb8c46b3cd1")
        private static Stereotype MDAASSOCDEP;

        @objid ("2fd59b0b-b732-40f2-a45a-80a2ed59264d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("37d829e5-dd34-4ed6-806e-665a1d85441f")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "a1a154ca-b239-4724-9953-8ca651e3ee64");
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
