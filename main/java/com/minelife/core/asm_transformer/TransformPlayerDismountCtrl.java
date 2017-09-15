package com.minelife.core.asm_transformer;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.*;

import org.objectweb.asm.tree.*;

public class TransformPlayerDismountCtrl implements IClassTransformer {

    public static boolean doBadMethod = false;

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        boolean isObfuscated = !name.equals(transformedName);

        if ("net.minecraft.entity.player.EntityPlayer".equals(transformedName)) {
            return transformPlayer(bytes, isObfuscated);
        }

        return bytes;
    }


    /**
     * Transforms the EntityPlayer.class by hooking into the updateRidden method and adding a call to _SAP_canDismountOnInput
     * <p>
     * in order for the ridden entity to control whether or not the rider can dismount via sneaking.
     *
     * @param bytes the class bytes to be transformed
     * @return the transformed class bytes
     */
    private static byte[] transformPlayer(byte[] bytes, boolean isObfuscated) {
        System.out.println("Transforming EntityPlayer");
        try {
            ClassNode classNode = new ClassNode();
            ClassReader classReader = new ClassReader(bytes);
            classReader.accept(classNode, 0);

            transformPlayerAgain(classNode, false);

            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            classNode.accept(classWriter);
            return classWriter.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bytes;
    }

    private static void transformPlayerAgain(ClassNode playerClass, boolean isObfuscated) {
        final String UPDATE_RIDDEN = isObfuscated ? "ab" : "updateRidden";
        final String UPDATE_RIDDEN_DESC = isObfuscated ? "()V" : "()V";

        MethodNode method = playerClass.methods.stream().filter(m -> m.name.equals(UPDATE_RIDDEN) && m.desc.equals(UPDATE_RIDDEN_DESC)).findFirst().orElse(null);
        if (method == null) return;


        AbstractInsnNode targetNode = null;
        for (AbstractInsnNode instruction : method.instructions.toArray()) {
            if (instruction.getOpcode() == Opcodes.ALOAD) {
                if (((VarInsnNode) instruction).var == 0 && instruction.getNext().getOpcode() == Opcodes.ACONST_NULL) {
                    targetNode = instruction;
                    break;
                }
            }
        }

        if (targetNode == null) return;

        /*
        mv.visitVarInsn(ALOAD, 0);
        mv.visitInsn(ACONST_NULL);
        mv.visitTypeInsn(CHECKCAST, "net/minecraft/entity/Entity");
        mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/entity/player/EntityPlayer", "mountEntity", "(Lnet/minecraft/entity/Entity;)V", false);
        Label l3 = new Label();
        mv.visitLabel(l3);
        mv.visitLineNumber(551, l3);
        mv.visitVarInsn(ALOAD, 0);
        mv.visitInsn(ICONST_0);
        mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/entity/player/EntityPlayer", "setSneaking", "(Z)V", false);
         */

        AbstractInsnNode popNode = targetNode;
        for (int i = 0; i < 10; i++)
        {
            popNode = popNode.getNext();
        }



        LabelNode newLabelNode = new LabelNode();

        InsnList toInsert = new InsnList();
        toInsert.add(new VarInsnNode(Opcodes.ALOAD, 0));
        toInsert.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(Hooks.class), "fireDismountEvent", isObfuscated ? "(Lsa;)Z" : "(Lnet/minecraft/entity/Entity;)Z", false));
        toInsert.add(new JumpInsnNode(Opcodes.IFEQ, newLabelNode));

        method.instructions.insertBefore(targetNode, toInsert);
        method.instructions.insert(popNode, newLabelNode);
    }

}
