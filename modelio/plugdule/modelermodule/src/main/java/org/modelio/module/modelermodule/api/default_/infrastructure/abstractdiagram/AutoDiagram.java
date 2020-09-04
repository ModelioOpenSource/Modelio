/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.infrastructure.abstractdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.diagrams.AbstractDiagram;
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
 * Proxy class to handle a {@link AbstractDiagram} with << AutoDiagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("b410fcf6-31df-4c16-b0e5-ff41e19d9852")
public class AutoDiagram {
    @objid ("fabe0742-8702-4156-b335-bd468e5342c6")
    public static final String STEREOTYPE_NAME = "AutoDiagram";

    /**
     * The underlying {@link AbstractDiagram} represented by this proxy, never null.
     */
    @objid ("f7ec5997-3f90-4bd2-8bbd-e61c09b76eb6")
    protected final AbstractDiagram elt;

    /**
     * Tells whether a {@link AutoDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link AbstractDiagram} stereotyped << AutoDiagram >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("495a671f-394e-42a8-8922-f110041f557e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof AbstractDiagram) && ((AbstractDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, AutoDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link AbstractDiagram} stereotyped << AutoDiagram >> then instantiate a {@link AutoDiagram} proxy.
     * 
     * @return a {@link AutoDiagram} proxy on the created {@link AbstractDiagram}.
     */
    @objid ("6644000a-b0d9-4f34-a171-6cef67dd4f6a")
    public static AutoDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("AbstractDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, AutoDiagram.STEREOTYPE_NAME);
        return AutoDiagram.instantiate((AbstractDiagram)e);
    }

    /**
     * Tries to instantiate a {@link AutoDiagram} proxy from a {@link AbstractDiagram} stereotyped << AutoDiagram >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a AbstractDiagram
     * @return a {@link AutoDiagram} proxy or <i>null</i>.
     */
    @objid ("8998570f-eac3-451e-b585-029236506b0c")
    public static AutoDiagram instantiate(AbstractDiagram obj) {
        return AutoDiagram.canInstantiate(obj) ? new AutoDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link AutoDiagram} proxy from a {@link AbstractDiagram} stereotyped << AutoDiagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link AbstractDiagram}
     * @return a {@link AutoDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b3075a3d-04a6-4944-9431-91ecc0ad6a1a")
    public static AutoDiagram safeInstantiate(AbstractDiagram obj) throws IllegalArgumentException {
        if (AutoDiagram.canInstantiate(obj))
        	return new AutoDiagram(obj);
        else
        	throw new IllegalArgumentException("AutoDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("baa4c361-884f-495f-89ef-5d5d1bfef444")
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
        AutoDiagram other = (AutoDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link AbstractDiagram}. 
     * @return the AbstractDiagram represented by this proxy, never null.
     */
    @objid ("c0e92896-5552-485d-88ea-a572d4b44663")
    public AbstractDiagram getElement() {
        return this.elt;
    }

    @objid ("2e640bef-a83d-4c2b-ba8b-e5471f2de879")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("bf67d7b4-405c-4519-9069-f8b4debb7109")
    protected AutoDiagram(AbstractDiagram elt) {
        this.elt = elt;
    }

    @objid ("1d38733b-2cc3-4edc-aec0-6c87d5b6b705")
    public static final class MdaTypes {
        @objid ("21f36087-c286-4331-9c67-599c98990ac9")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("449a911a-2f3c-4680-a85f-c5e5fab0f717")
        private static Stereotype MDAASSOCDEP;

        @objid ("379d62ed-8f82-4b7b-b3b3-d97a23da2ddb")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0a32ebba-3df7-4684-baa4-ac8fe154b77d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "8fb43b1c-7819-11e1-a4f1-002564c97630");
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
