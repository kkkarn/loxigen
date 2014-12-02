//:: # Copyright 2013, Big Switch Networks, Inc.
//:: #
//:: # LoxiGen is licensed under the Eclipse Public License, version 1.0 (EPL), with
//:: # the following special exception:
//:: #
//:: # LOXI Exception
//:: #
//:: # As a special exception to the terms of the EPL, you may distribute libraries
//:: # generated by LoxiGen (LoxiGen Libraries) under the terms of your choice, provided
//:: # that copyright and licensing notices generated by LoxiGen are not altered or removed
//:: # from the LoxiGen Libraries and the notice provided below is (i) included in
//:: # the LoxiGen Libraries, if distributed in source code form and (ii) included in any
//:: # documentation for the LoxiGen Libraries, if distributed in binary form.
//:: #
//:: # Notice: "Copyright 2013, Big Switch Networks, Inc. This library was generated by the LoxiGen Compiler."
//:: #
//:: # You may not use this file except in compliance with the EPL or LOXI Exception. You may obtain
//:: # a copy of the EPL at:
//:: #
//:: # http::: #www.eclipse.org/legal/epl-v10.html
//:: #
//:: # Unless required by applicable law or agreed to in writing, software
//:: # distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
//:: # WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
//:: # EPL for the specific language governing permissions and limitations
//:: # under the EPL.
//::
//:: import itertools
//:: import re
//:: import os
//:: include('_copyright.java')

//:: include('_autogen.java')

package ${msg.package};

//:: include("_imports.java", msg=msg)

public interface ${msg.name}${ "<%s>" % msg.type_annotation if msg.type_annotation else ""} extends ${", ".join(msg.all_parent_interfaces)} {
//:: for prop in msg.members:
    ${prop.java_type.public_type} ${prop.getter_name}()${ "" if prop.is_universal else " throws UnsupportedOperationException"};
//:: #endfor
//:: if os.path.exists("%s/custom/interface/%s.java" % (template_dir, msg.name)):
//:: include("custom/interface/%s.java" % msg.name, msg=msg)
//:: #endif
    

    void writeTo(ChannelBuffer channelBuffer);

    Builder${msg.type_variable} createBuilder();
    //:: simple_type, annotation = re.match(r'(\w+)(<.*>)?', msg.parent_interface).groups() if msg.parent_interface else ("", "")
    public interface Builder${ "<%s>" % msg.type_annotation if msg.type_annotation else ""} ${"extends %s.Builder" % simple_type if msg.parent_interface else ""}${annotation if annotation else ""} {
        ${msg.name}${msg.type_variable} build();
//:: for prop in msg.members:
        ${prop.java_type.public_type} ${prop.getter_name}()${ "" if prop.is_universal else " throws UnsupportedOperationException"};
//:: if prop.is_writeable:
        Builder${msg.type_variable} ${prop.setter_name}(${prop.java_type.public_type} ${prop.name})${ "" if prop.is_universal else " throws UnsupportedOperationException"};
//:: #endif
//:: #endfor
    }
}
