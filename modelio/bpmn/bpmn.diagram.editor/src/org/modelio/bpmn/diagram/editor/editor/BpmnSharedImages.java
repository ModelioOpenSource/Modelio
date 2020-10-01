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

package org.modelio.bpmn.diagram.editor.editor;

import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.modelio.bpmn.diagram.editor.plugin.DiagramEditorBpmn;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.events.BpmnCancelEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnCompensateEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnConditionalEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
import org.modelio.metamodel.bpmn.events.BpmnErrorEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEscalationEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnLinkEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnMessageEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnSignalEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.events.BpmnTerminateEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnTimerEventDefinition;
import org.modelio.metamodel.bpmn.gateways.BpmnComplexGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnEventBasedGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnExclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnInclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnParallelGateway;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.osgi.framework.Bundle;

/**
 * BPMN diagram shared images registry.
 */
@objid ("60743184-55b6-11e2-877f-002564c97630")
public class BpmnSharedImages implements ISharedImages {
    @objid ("60743186-55b6-11e2-877f-002564c97630")
    public static final String SUBPROCESS = "Static_SubProcess";

    @objid ("60743188-55b6-11e2-877f-002564c97630")
    public static final String LOOP = "Static_Loop";

    @objid ("6074318a-55b6-11e2-877f-002564c97630")
    public static final String SEQUENTIAL = "Static_Sequential";

    @objid ("6074318c-55b6-11e2-877f-002564c97630")
    public static final String PARALLEL = "Static_Parallel";

    @objid ("6074318e-55b6-11e2-877f-002564c97630")
    public static final String ADHOC = "Static_AdHoc";

    @objid ("60743190-55b6-11e2-877f-002564c97630")
    public static final String COMPENSATION = "Static_Compensation";

    @objid ("60743192-55b6-11e2-877f-002564c97630")
    public static final String MESSAGE = "Static_Message";

    @objid ("60743194-55b6-11e2-877f-002564c97630")
    public static final String MESSAGERETURN = "Static_MessageReturn";

    @objid ("60743196-55b6-11e2-877f-002564c97630")
    public static final String INPUT = "Static_Input";

    @objid ("60743198-55b6-11e2-877f-002564c97630")
    public static final String OUTPUT = "Static_Output";

    @objid ("6074319a-55b6-11e2-877f-002564c97630")
    public static final String STORE = "Static_Store";

    @objid ("6074319d-55b6-11e2-877f-002564c97630")
    public static final String SENDTASKHEADER = "Static_SendTask_Header";

    @objid ("6075b7fa-55b6-11e2-877f-002564c97630")
    public static final String RECEIVETASKHEADER = "Static_ReceiveTask_Header";

    @objid ("6075b7fc-55b6-11e2-877f-002564c97630")
    public static final String SERVICETASKHEADER = "Static_ServiceTask_Header";

    @objid ("6075b7fe-55b6-11e2-877f-002564c97630")
    public static final String USERTASKHEADER = "Static_UserTask_Header";

    @objid ("6075b800-55b6-11e2-877f-002564c97630")
    public static final String MANUALTASKHEADER = "Static_ManualTask_Header";

    @objid ("6075b802-55b6-11e2-877f-002564c97630")
    public static final String SCRIPTTASKHEADER = "Static_SvriptTask_Header";

    @objid ("6075b804-55b6-11e2-877f-002564c97630")
    public static final String BUSINESSRULETASKHEADER = "Static_BusinessRuleTask_Header";

    @objid ("6075b806-55b6-11e2-877f-002564c97630")
    public static String NONEMPTYSUBPROCESS = "Static_NonEmptySubProcess";

    @objid ("45a4c932-5bb7-4f25-8db2-f1efb332e711")
    private static final String SEPARATOR = "_";

    @objid ("958121e3-2c71-4a77-8a6e-e330ce37ec1f")
    private static final String NI_SUFFIX = "_ni";

    @objid ("35234d57-09a0-415d-bb7c-af97f8c07280")
    private static final String MULTIPLE_SUFFIX = "_Multiple";

    /**
     * Images for colorizable figures, having a typical 40 * 40 size.
     */
    @objid ("8cec8099-f720-45f6-9b8d-317b77ce4e1e")
    private static final String MEDIUM_IMAGES_SUBDIR = "images/medium";

    /**
     * Images for colorizable figures, having a typical 256 * 256 size.
     */
    @objid ("ff7be6e9-2073-4a41-a128-c5f7a9cb67d7")
    @SuppressWarnings ("unused")
    private static final String BIG_IMAGES_SUBDIR = "images/big";

    /**
     * Other images, having a typical 16 * 16 size.
     */
    @objid ("70395b02-6f09-4c0f-81f1-988b4135a994")
    private static final String SMALL_ICONS_SUBDIR = "icons/";

    @objid ("311df532-c696-4e00-a1d5-5bd0c9c34e31")
    private static final String PARALLEL_SUFFIX = "_Parallel";

    @objid ("d4b64e72-f38b-4370-a514-092c66a9cf4d")
    public static final String COLLECTION = "Static_Collection";

    @objid ("8fc2f30d-dba5-47da-9a57-50d1707e8a3c")
    private static final String SMALL_IMAGES_SUBDIR = "images/";

    @objid ("6074319c-55b6-11e2-877f-002564c97630")
    private ImageRegistry imageRegistry;

    @objid ("6075b807-55b6-11e2-877f-002564c97630")
    public BpmnSharedImages() {
        initializeImageRegistry();
    }

    @objid ("6075b809-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage(String key) {
        return this.imageRegistry.get(key);
    }

    @objid ("6075b80f-55b6-11e2-877f-002564c97630")
    @Override
    public ImageDescriptor getImageDescriptor(String key) {
        return this.imageRegistry.getDescriptor(key);
    }

    @objid ("6075b815-55b6-11e2-877f-002564c97630")
    public void declareImage(String key, ImageDescriptor descriptor) {
        this.imageRegistry.put(key, descriptor);
    }

    @objid ("6075b819-55b6-11e2-877f-002564c97630")
    private void initializeImageRegistry() {
        this.imageRegistry = new ImageRegistry(Display.getDefault());
        this.imageRegistry.put("MissingImage", ImageDescriptor.getMissingImageDescriptor());
        Bundle bundle = Platform.getBundle(DiagramEditorBpmn.PLUGIN_ID);
        
        ImageDescriptor image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/bpmnexclusivegateway40x40.png"), null));
        declareImage(BpmnExclusiveGateway.MNAME, image);
        
        // Variant of the BpmnExclusiveGateway image is currently not used
        // image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/bpmnexclusivegateway2_40x40.png"), null));
        // declareImage(BpmnExclusiveGateway.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/bpmnparallelgateway40x40.png"), null));
        declareImage(BpmnParallelGateway.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/bpmninclusivegateway40x40.png"), null));
        declareImage(BpmnInclusiveGateway.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/bpmneventbasedgateway40x40.png"), null));
        declareImage(BpmnEventBasedGateway.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/bpmncomplexgateway40x40.png"), null));
        declareImage(BpmnComplexGateway.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/startevent32x32.png"), null));
        declareImage(BpmnStartEvent.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/endevent32x32.png"), null));
        declareImage(BpmnEndEvent.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/bpmncomplexgateway40x40.png"), null));
        declareImage(BpmnComplexGateway.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/intermediaryevent_tr32x32.png"), null));
        declareImage(BpmnIntermediateThrowEvent.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/intermediaryevent_ca32x32.png"), null));
        declareImage(BpmnIntermediateCatchEvent.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/intermediaryevent_ca32x32.png"), null));
        declareImage(BpmnBoundaryEvent.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/message_intermediaryevent_tr32x32.png"), null));
        declareImage(BpmnIntermediateThrowEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnMessageEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/message_startevent_ni32x32.png"), null));
        declareImage(BpmnStartEvent.MNAME + BpmnSharedImages.NI_SUFFIX + BpmnSharedImages.SEPARATOR + BpmnMessageEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/startevent_ni32x32.png"), null));
        declareImage(BpmnStartEvent.MNAME + BpmnSharedImages.NI_SUFFIX, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/message_endevent32x32.png"), null));
        declareImage(BpmnEndEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnMessageEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/message_intermediaryevent_ca32x32.png"), null));
        declareImage(BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnMessageEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/message_intermediaryevent_ni32x32.png"), null));
        declareImage(BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.NI_SUFFIX + BpmnSharedImages.SEPARATOR + BpmnMessageEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/message_startevent32x32.png"), null));
        declareImage(BpmnStartEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnMessageEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/timer_startevent32x32.png"), null));
        declareImage(BpmnStartEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnTimerEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/timer_startevent_ni32x32.png"), null));
        declareImage(BpmnStartEvent.MNAME + BpmnSharedImages.NI_SUFFIX + BpmnSharedImages.SEPARATOR + BpmnTimerEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/timer_intermediaryevent_ca32x32.png"), null));
        declareImage(BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnTimerEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/timer_intermediaryevent_ni32x32.png"), null));
        declareImage(BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.NI_SUFFIX + BpmnSharedImages.SEPARATOR + BpmnTimerEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/error_startevent32x32.png"), null));
        declareImage(BpmnStartEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnErrorEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/error_endevent32x32.png"), null));
        declareImage(BpmnEndEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnErrorEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/error_intermediaryevent_ca32x32.png"), null));
        declareImage(BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnErrorEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/escalation_intermediaryevent_tr32x32.png"), null));
        declareImage(BpmnIntermediateThrowEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnEscalationEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/escalation_intermediaryevent_ca32x32.png"), null));
        declareImage(BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnEscalationEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/escalation_intermediaryevent_ni32x32.png"), null));
        declareImage(BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.NI_SUFFIX + BpmnSharedImages.SEPARATOR + BpmnEscalationEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/escalation_startevent32x32.png"), null));
        declareImage(BpmnStartEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnEscalationEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/escalation_startevent_ni32x32.png"), null));
        declareImage(BpmnStartEvent.MNAME + BpmnSharedImages.NI_SUFFIX + BpmnSharedImages.SEPARATOR + BpmnEscalationEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/escalation_endevent32x32.png"), null));
        declareImage(BpmnEndEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnEscalationEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/cancel_endevent32x32.png"), null));
        declareImage(BpmnEndEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnCancelEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/cancel_intermediaryevent_ca32x32.png"), null));
        declareImage(BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnCancelEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/compensation_intermediaryevent_th32x32.png"), null));
        declareImage(BpmnIntermediateThrowEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnCompensateEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/compensation_endevent32x32.png"), null));
        declareImage(BpmnEndEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnCompensateEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/compensation_intermediaryevent_ca32x32.png"), null));
        declareImage(BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnCompensateEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/compensation_startevent32x32.png"), null));
        declareImage(BpmnStartEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnCompensateEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/conditional_startevent32x32.png"), null));
        declareImage(BpmnStartEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnConditionalEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/conditional_startevent_ni32x32.png"), null));
        declareImage(BpmnStartEvent.MNAME + BpmnSharedImages.NI_SUFFIX + BpmnSharedImages.SEPARATOR + BpmnConditionalEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/conditional_intermediaryevent_ca32x32.png"), null));
        declareImage(BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnConditionalEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/conditional_intermediaryevent_ni32x32.png"), null));
        declareImage(BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.NI_SUFFIX + BpmnSharedImages.SEPARATOR + BpmnConditionalEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/link_intermediaryevent_ca32x32.png"), null));
        declareImage(BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnLinkEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/link_intermediaryevent_tr32x32.png"), null));
        declareImage(BpmnIntermediateThrowEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnLinkEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/signal_intermediaryevent_tr32x32.png"), null));
        declareImage(BpmnIntermediateThrowEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnSignalEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/signal_endevent32x32.png"), null));
        declareImage(BpmnEndEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnSignalEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/signal_intermediaryevent_ca32x32.png"), null));
        declareImage(BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnSignalEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/signal_intermediaryevent_ni32x32.png"), null));
        declareImage(BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.NI_SUFFIX + BpmnSharedImages.SEPARATOR + BpmnSignalEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/signal_startevent32x32.png"), null));
        declareImage(BpmnStartEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnSignalEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/signal_startevent_ni32x32.png"), null));
        declareImage(BpmnStartEvent.MNAME + BpmnSharedImages.NI_SUFFIX + BpmnSharedImages.SEPARATOR + BpmnSignalEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/terminate_endevent32x32.png"), null));
        declareImage(BpmnEndEvent.MNAME + BpmnSharedImages.SEPARATOR + BpmnTerminateEventDefinition.MNAME, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/multiple_startevent32x32.png"), null));
        declareImage(BpmnStartEvent.MNAME + BpmnSharedImages.MULTIPLE_SUFFIX, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/multiple_parallel_startevent32x32.png"), null));
        declareImage(BpmnStartEvent.MNAME + BpmnSharedImages.PARALLEL_SUFFIX + BpmnSharedImages.MULTIPLE_SUFFIX, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/multiple_startevent_ni32x32.png"), null));
        declareImage(BpmnStartEvent.MNAME + BpmnSharedImages.NI_SUFFIX + BpmnSharedImages.MULTIPLE_SUFFIX, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/multiple_parallel_startevent_ni32x32.png"), null));
        declareImage(BpmnStartEvent.MNAME + BpmnSharedImages.NI_SUFFIX + BpmnSharedImages.PARALLEL_SUFFIX + BpmnSharedImages.MULTIPLE_SUFFIX, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/multiple_intermediaryevent_ca32x32.png"), null));
        declareImage(BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.MULTIPLE_SUFFIX, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/multiple_parallel_intermediaryevent_ca32x32.png"), null));
        declareImage(BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.PARALLEL_SUFFIX + BpmnSharedImages.MULTIPLE_SUFFIX, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/multiple_intermediaryevent_ni32x32.png"), null));
        declareImage(BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.NI_SUFFIX + BpmnSharedImages.MULTIPLE_SUFFIX, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/multiple_parallel_intermediaryevent_ni32x32.png"), null));
        declareImage(BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.NI_SUFFIX + BpmnSharedImages.PARALLEL_SUFFIX + BpmnSharedImages.MULTIPLE_SUFFIX, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/intermediaryevent_ni32x32.png"), null));
        declareImage(BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.NI_SUFFIX, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/multiple_intermediaryevent_tr32x32.png"), null));
        declareImage(BpmnIntermediateThrowEvent.MNAME + BpmnSharedImages.MULTIPLE_SUFFIX, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/multiple_endevent32x32.png"), null));
        declareImage(BpmnEndEvent.MNAME + BpmnSharedImages.MULTIPLE_SUFFIX, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/message32x32.png"), null));
        declareImage(BpmnSharedImages.MESSAGE, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/message_return32x32.png"), null));
        declareImage(BpmnSharedImages.MESSAGERETURN, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/input32x32.png"), null));
        declareImage(BpmnSharedImages.INPUT, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/collection32x32.png"), null));
        declareImage(BpmnSharedImages.COLLECTION, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/output32x32.png"), null));
        declareImage(BpmnSharedImages.OUTPUT, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.MEDIUM_IMAGES_SUBDIR + "/store32x32.png"), null));
        declareImage(BpmnSharedImages.STORE, image);
        
        // Images for headers
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.SMALL_ICONS_SUBDIR + "receivetask_header.png"), null));
        declareImage(BpmnSharedImages.RECEIVETASKHEADER, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.SMALL_ICONS_SUBDIR + "sendtask_header.png"), null));
        declareImage(BpmnSharedImages.SENDTASKHEADER, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.SMALL_ICONS_SUBDIR + "servicetask_header.png"), null));
        declareImage(BpmnSharedImages.SERVICETASKHEADER, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.SMALL_ICONS_SUBDIR + "usertask_header.png"), null));
        declareImage(BpmnSharedImages.USERTASKHEADER, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.SMALL_ICONS_SUBDIR + "manualtask_header.png"), null));
        declareImage(BpmnSharedImages.MANUALTASKHEADER, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.SMALL_ICONS_SUBDIR + "scripttask_header.png"), null));
        declareImage(BpmnSharedImages.SCRIPTTASKHEADER, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.SMALL_ICONS_SUBDIR + "businessruletask_header.png"), null));
        declareImage(BpmnSharedImages.BUSINESSRULETASKHEADER, image);
        
        // Images for BPMN activity
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.SMALL_ICONS_SUBDIR + "activity_subprocess.png"), null));
        declareImage(BpmnSharedImages.SUBPROCESS, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.SMALL_ICONS_SUBDIR + "activity_subprocess_content.png"), null));
        declareImage(BpmnSharedImages.NONEMPTYSUBPROCESS, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.SMALL_IMAGES_SUBDIR + "activity_loop12x12.png"), null));
        declareImage(BpmnSharedImages.LOOP, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.SMALL_IMAGES_SUBDIR + "activity_parallel12x12.png"), null));
        declareImage(BpmnSharedImages.PARALLEL, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.SMALL_IMAGES_SUBDIR + "activity_sequential12x12.png"), null));
        declareImage(BpmnSharedImages.SEQUENTIAL, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.SMALL_IMAGES_SUBDIR + "activity_adhock12x12.png"), null));
        declareImage(BpmnSharedImages.ADHOC, image);
        
        image = ImageDescriptor.createFromURL(FileLocator.find(bundle, new Path(BpmnSharedImages.SMALL_IMAGES_SUBDIR + "activity_compensation12x12.png"), null));
        declareImage(BpmnSharedImages.COMPENSATION, image);
    }

    @objid ("dc19f6e0-19bb-4727-95d0-f46392e065aa")
    private static String getImageRef(BpmnStartEvent startEvent) {
        if (startEvent != null && startEvent.isValid()) {
            List<BpmnEventDefinition> definitions = startEvent.getEventDefinitions();
        
            if (startEvent.isIsInterrupting()) {
                if (definitions.size() == 1) {
                    BpmnEventDefinition definition = definitions.get(0);
                    return BpmnStartEvent.MNAME + BpmnSharedImages.SEPARATOR + definition.getMClass().getName();
                } else if (definitions.size() > 1) {
                    if (startEvent.isParallelMultiple()) {
                        return BpmnStartEvent.MNAME + BpmnSharedImages.PARALLEL_SUFFIX + BpmnSharedImages.MULTIPLE_SUFFIX;
                    } else {
                        return BpmnStartEvent.MNAME + BpmnSharedImages.MULTIPLE_SUFFIX;
                    }
                }
            } else {
                if (definitions.size() == 1) {
                    BpmnEventDefinition definition = definitions.get(0);
                    return BpmnStartEvent.MNAME + BpmnSharedImages.NI_SUFFIX + BpmnSharedImages.SEPARATOR + definition.getMClass().getName();
                } else if (definitions.size() > 1) {
                    if (startEvent.isParallelMultiple()) {
                        return BpmnStartEvent.MNAME + BpmnSharedImages.NI_SUFFIX + BpmnSharedImages.PARALLEL_SUFFIX + BpmnSharedImages.MULTIPLE_SUFFIX;
                    } else {
                        return BpmnStartEvent.MNAME + BpmnSharedImages.NI_SUFFIX + BpmnSharedImages.MULTIPLE_SUFFIX;
                    }
                }
            }
        }
        return BpmnStartEvent.MNAME;
    }

    @objid ("1134e093-0e6d-45fb-8aae-d0e4aa290d35")
    private static String getImageRef(BpmnEndEvent endEvent) {
        if (endEvent != null && endEvent.isValid()) {
            List<BpmnEventDefinition> definitions = endEvent.getEventDefinitions();
        
            if (definitions.size() == 1) {
                BpmnEventDefinition definition = definitions.get(0);
                return BpmnEndEvent.MNAME + BpmnSharedImages.SEPARATOR + definition.getMClass().getName();
            } else if (definitions.size() > 1) {
                return BpmnEndEvent.MNAME + BpmnSharedImages.MULTIPLE_SUFFIX;
            }
        }
        return BpmnEndEvent.MNAME;
    }

    @objid ("0feca065-220c-4590-8d7c-20d7dd65bfd7")
    private static String getImageRef(BpmnBoundaryEvent bdEvent) {
        if (bdEvent != null && bdEvent.isValid()) {
            List<BpmnEventDefinition> definitions = bdEvent.getEventDefinitions();
        
            if (bdEvent.isCancelActivity()) {
                if (definitions.size() == 1) {
                    BpmnEventDefinition definition = definitions.get(0);
                    return BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.SEPARATOR + definition.getMClass().getName();
                } else if (definitions.size() > 1) {
                    if (bdEvent.isParallelMultiple()) {
                        return BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.PARALLEL_SUFFIX + BpmnSharedImages.MULTIPLE_SUFFIX;
                    } else {
                        return BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.MULTIPLE_SUFFIX;
                    }
                }
        
            } else {
                if (definitions.size() == 1) {
                    BpmnEventDefinition definition = definitions.get(0);
                    return BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.NI_SUFFIX + BpmnSharedImages.SEPARATOR + definition.getMClass().getName();
                } else if (definitions.size() > 1) {
                    if (bdEvent.isParallelMultiple()) {
                        return BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.NI_SUFFIX + BpmnSharedImages.PARALLEL_SUFFIX + BpmnSharedImages.MULTIPLE_SUFFIX;
                    } else {
                        return BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.NI_SUFFIX + BpmnSharedImages.MULTIPLE_SUFFIX;
                    }
                } else {
                    return BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.NI_SUFFIX;
                }
            }
        }
        return BpmnBoundaryEvent.class.getName();
    }

    @objid ("1007fe1b-16bf-445b-b87a-1d7b1ee3bc96")
    private static String getImageRef(BpmnIntermediateCatchEvent catchEvent) {
        List<BpmnEventDefinition> definitions = catchEvent.getEventDefinitions();
        
        if (definitions.size() == 1) {
            BpmnEventDefinition definition = definitions.get(0);
            return BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.SEPARATOR + definition.getMClass().getName();
        } else if (definitions.size() > 1) {
            if (catchEvent.isParallelMultiple()) {
                return BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.PARALLEL_SUFFIX + BpmnSharedImages.MULTIPLE_SUFFIX;
            } else {
                return BpmnIntermediateCatchEvent.MNAME + BpmnSharedImages.MULTIPLE_SUFFIX;
            }
        }
        return BpmnIntermediateCatchEvent.MNAME;
    }

    @objid ("e99c8090-48c8-4a1f-b74f-841f00287372")
    private static String getImageRef(BpmnIntermediateThrowEvent throwEvent) {
        if (throwEvent != null && throwEvent.isValid()) {
            List<BpmnEventDefinition> definitions = throwEvent.getEventDefinitions();
        
            if (definitions.size() == 1) {
                BpmnEventDefinition definition = definitions.get(0);
                return BpmnIntermediateThrowEvent.MNAME + BpmnSharedImages.SEPARATOR + definition.getMClass().getName();
            } else if (definitions.size() > 1) {
                return BpmnIntermediateThrowEvent.MNAME + BpmnSharedImages.MULTIPLE_SUFFIX;
            }
        }
        return BpmnIntermediateThrowEvent.MNAME;
    }

    @objid ("11f65421-1dd4-4183-8f18-5afea59e1de3")
    public static String getImageRef(MObject elt) {
        if (elt instanceof BpmnStartEvent) {
            return BpmnSharedImages.getImageRef((BpmnStartEvent) elt);
        } else if (elt instanceof BpmnEndEvent) {
            return BpmnSharedImages.getImageRef((BpmnEndEvent) elt);
        } else if (elt instanceof BpmnBoundaryEvent) {
            return BpmnSharedImages.getImageRef((BpmnBoundaryEvent) elt);
        } else if (elt instanceof BpmnIntermediateCatchEvent) {
            return BpmnSharedImages.getImageRef((BpmnIntermediateCatchEvent) elt);
        } else if (elt instanceof BpmnIntermediateThrowEvent) {
            return BpmnSharedImages.getImageRef((BpmnIntermediateThrowEvent) elt);
        } else if (elt != null) {
            return elt.getMClass().getName();
        } else {
            return "";
        }
    }

    @objid ("65e4ade4-c61a-46e0-a621-caa3e277514e")
    public static final class BpmnImageRefHolder {
        @objid ("921d54eb-25a9-41fb-a2d0-b5790b625cc6")
        private String image_ref = null;

        /**
         * @return the image ref for a {@link MObject}.
         */
        @objid ("f4824f2c-fc0e-4966-aba7-2e37660541b3")
        public String getImageRef(MObject elt) {
            return BpmnSharedImages.getImageRef(elt);
        }

        /**
         * Compute a new image ref for a {@link MObject}, and store it.
         * @param model a gm model.
         * 
         * @return <code>true</code> if the ref changed.
         */
        @objid ("ebc15923-2da1-4b81-9941-5ce06b52ebcc")
        public boolean updateImageRef(MObject elt) {
            String new_ref = getImageRef(elt);
            if (!Objects.equals(this.image_ref, new_ref)) {
                this.image_ref = new_ref;
                return true;
            } else {
                return false;
            }
        }

    }

}
