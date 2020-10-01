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

package org.modelio.metamodel.mmextensions.standard.facilities.interaction;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream.Builder;
import java.util.stream.Stream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;

/**
 * Service that attributes a sequence number to all {@link Message messages} of an {@link Interaction}.
 * <p>
 * The sequence numbers are computed once on instantiation and won't be updated if the model is modified.
 * <p>
 * The sequence number is an integer matching the index in the sequence.
 * The sequence order is determined by: <ol>
 * <li> The vertical position of the message.
 * <li> When 2 messages have same vertical position they are sorted by name.
 * <li> In last resort they are sorted by identifier.
 * </ol>
 * @author cma
 * @since 3.7.1
 */
@objid ("3d7b8ed4-22bf-4ba0-a0f0-a0ba9acab418")
public class MessageSequencer {
    @objid ("b34a544b-86db-4e64-99aa-4663bde174e6")
    private final Map<Message, String> sequences;

    @objid ("e6c5a7a0-b74c-4834-b258-30637ea27eac")
    private final Interaction interaction;

    @objid ("c1b77b94-3d89-465d-901b-9fb081561f2e")
    public MessageSequencer(Interaction interaction) {
        this.interaction = interaction;
        this.sequences = compute();
    }

    /**
     * Get the sequence number for a message
     * 
     * @param m a message
     * @return its sequence number.
     * @throws java.lang.IllegalArgumentException if the message is not part of the interaction.
     */
    @objid ("d2b5c9a3-8a02-411f-8159-3acd3ace34dc")
    public String getSequence(Message m) throws IllegalArgumentException {
        String seq = this.sequences.get(m);
        
        if (seq == null) {
            throw new IllegalArgumentException(String.format("%s not in %s", m, this.interaction));
        }
        return seq;
    }

    /**
     * Update the {@link Message#getSequence()} for each message.
     */
    @objid ("381fc833-463b-420f-9b8a-ca784e768148")
    public void updateModel() {
        //Log.trace(toString());
        
        for (Entry<Message, String> entry : this.sequences.entrySet()) {
            Message msg = entry.getKey();
            String newSeq = entry.getValue();
            if (! Objects.equals(msg.getSequence(), newSeq)) {
                msg.setSequence(newSeq);
            }
        }
    }

    @objid ("d8e46003-a8e9-4984-a2bd-bcf96f24f2bf")
    private static int compareMessages(Message a, Message b) {
        MessageEnd as = a.getSendEvent();
        MessageEnd bs = b.getSendEvent();
        if (as==null) {
            as = a.getReceiveEvent();
        }
        if (bs==null) {
            bs = b.getReceiveEvent();
        }
        
        int al = as == null ? Integer.MAX_VALUE : as.getLineNumber();
        int bl = bs == null ? Integer.MAX_VALUE : bs.getLineNumber();
        int d = al - bl;
        if (d == 0) {
            d = a.getName().compareTo(b.getName());
        }
        
        if (d == 0) {
            return a.getUuid().compareTo(b.getUuid());
        }
        return d;
    }

    /**
     * Compute the messages sequence number.
     * 
     * @return the sequence map
     */
    @objid ("0152a228-5771-46cb-a8f4-3f4f59edca86")
    private Map<Message, String> compute() {
        int i = 1;
        TreeSet<Message> sortedSet = InteractionHelper.allInteractionFragments(this.interaction)
        .flatMap(MessageSequencer::getMessages)
        .collect(Collectors.toCollection(() -> new TreeSet<>(MessageSequencer::compareMessages)))
        ;
        
        Map<Message, String> ret = new HashMap<>(sortedSet.size());
        
        for(Message m : sortedSet) {
            ret.put(m, String.valueOf(i));
            i++;
        };
        return ret;
    }

    @objid ("33e83ab5-79de-475e-b460-ff6108db1b14")
    private static Stream<? extends Message> getMessages(InteractionFragment f) {
        if (f instanceof MessageEnd) {
            MessageEnd me = (MessageEnd)f;
            Builder<Message> sb = Stream.<Message>builder();
            if (me.getSentMessage() != null) {
                sb.add(me.getSentMessage());
            }
            if (me.getReceivedMessage() != null) {
                sb.add(me.getReceivedMessage());
            }
            return sb.build();
        } else {
            return Stream.empty();
        }
    }

    @objid ("b7abf7c0-2043-4cbb-a34e-2ccbbc5bdd1c")
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(getClass().getSimpleName()).append("[");
        s.append("interaction=").append(this.interaction).append("\n");
        for (Entry<Message, String> entry : this.sequences.entrySet()) {
            Message m = entry.getKey();
            MessageEnd src = m.getSendEvent();
            MessageEnd target = m.getReceiveEvent();
            s.append("\t").append(m).append(" from ").append(dump(src,s)).append(" to ").append(dump(target,s)).append("\n");
            s.append("\t\t seq='").append(m.getSequence()).append("' ==> '").append(entry.getValue()).append("'\n");
        }
        s.append("]");
        return s.toString();
    }

    @objid ("e42d1bde-efb0-4c58-8ab6-1dd300bc67d6")
    private static String dump(MessageEnd p, StringBuilder s) {
        if (p==null) {
            s.append("<null>");
        } else {
            s.append(p).append("[line=").append(p.getLineNumber()).append("]");
        }
        return "";
    }

}
