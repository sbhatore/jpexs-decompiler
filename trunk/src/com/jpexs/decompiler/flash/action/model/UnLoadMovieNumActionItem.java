/*
 * Copyright (C) 2013 JPEXS
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jpexs.decompiler.flash.action.model;

import com.jpexs.decompiler.flash.action.parser.script.ActionSourceGenerator;
import com.jpexs.decompiler.flash.action.swf4.ActionGetURL2;
import com.jpexs.decompiler.flash.action.swf4.ActionPush;
import com.jpexs.decompiler.flash.action.model.operations.AddActionItem;
import com.jpexs.decompiler.graph.GraphSourceItem;
import com.jpexs.decompiler.graph.GraphTargetItem;
import com.jpexs.decompiler.graph.SourceGenerator;
import java.util.List;

/**
 *
 * @author JPEXS
 */
public class UnLoadMovieNumActionItem extends ActionItem {

    private GraphTargetItem num;

    public UnLoadMovieNumActionItem(GraphSourceItem instruction, GraphTargetItem num) {
        super(instruction, PRECEDENCE_PRIMARY);
        this.num = num;
    }

    @Override
    public String toString(ConstantPool constants) {
        return hilight("unloadMovieNum(") + num + hilight(")");
    }

    @Override
    public List<GraphSourceItem> toSource(List<Object> localData, SourceGenerator generator) {
        ActionSourceGenerator asGenerator = (ActionSourceGenerator) generator;
        return toSourceMerge(localData, generator, new ActionPush(""), new AddActionItem(src, asGenerator.pushConstTargetItem("_level"), num, true), new ActionGetURL2(0, true, false));
    }

    @Override
    public boolean hasReturnValue() {
        return false;
    }
}