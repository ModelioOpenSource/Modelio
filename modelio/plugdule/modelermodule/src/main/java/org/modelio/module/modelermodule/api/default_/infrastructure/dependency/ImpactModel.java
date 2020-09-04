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
 * Proxy class to handle a {@link Dependency} with << impact_model >> stereotype.
 * <p>Stereotype description:
 * <br/><i>impact_model dependencies are used to associate one or several ImpactModel instances to an ImpactDiagram. The associated models are used to filter the impact links to be displayed in the diagram.</i></p>
 */
@objid ("570c43d0-4d3e-4aa8-89b0-108c1cd48ed9")
public class ImpactModel {
    @objid ("f9c9ee7f-44a5-419e-8c84-c6c29bc98c62")
    public static final String STEREOTYPE_NAME = "impact_model";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("4cdf72f4-f4c8-46e1-ba23-27ac555879ac")
    protected final Dependency elt;

    /**
     * Tells whether a {@link ImpactModel proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << impact_model >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("97431edf-8899-425e-9b87-01ffa50cebd8")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ImpactModel.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << impact_model >> then instantiate a {@link ImpactModel} proxy.
     * 
     * @return a {@link ImpactModel} proxy on the created {@link Dependency}.
     */
    @objid ("f2bcf2e3-a358-4949-a200-d3d026c5c64a")
    public static ImpactModel create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ImpactModel.STEREOTYPE_NAME);
        return ImpactModel.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link ImpactModel} proxy from a {@link Dependency} stereotyped << impact_model >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link ImpactModel} proxy or <i>null</i>.
     */
    @objid ("bd19d23e-580f-4b60-add5-4445aa316460")
    public static ImpactModel instantiate(Dependency obj) {
        return ImpactModel.canInstantiate(obj) ? new ImpactModel(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ImpactModel} proxy from a {@link Dependency} stereotyped << impact_model >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link ImpactModel} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c0200958-2297-4e97-adfe-361900af3602")
    public static ImpactModel safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (ImpactModel.canInstantiate(obj))
        	return new ImpactModel(obj);
        else
        	throw new IllegalArgumentException("ImpactModel: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ee45a63a-84bb-484f-9d43-b8ce65e3a2a8")
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
        ImpactModel other = (ImpactModel) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("5306039f-8f0b-4a93-af86-8ac02f93209d")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("d88f6161-7c50-4b9c-93e5-3bb23d8ca932")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("5b5facdb-cb2a-4e13-bb66-f39bd1091872")
    protected ImpactModel(Dependency elt) {
        this.elt = elt;
    }

    @objid ("2f2f1126-cb55-4153-a6f5-b83673c9034b")
    public static final class MdaTypes {
        @objid ("69e180b8-77ab-46c3-b7e1-c3b170e109b1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("679b85de-be9f-4013-bd42-99abff7d9f5d")
        private static Stereotype MDAASSOCDEP;

        @objid ("7d60a9e3-71ee-4422-8362-63467efb626e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("03d27cf0-2d20-409f-9931-6e659b18e570")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "946458b2-daf1-44b8-887b-12a0d9e5c2f6");
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
