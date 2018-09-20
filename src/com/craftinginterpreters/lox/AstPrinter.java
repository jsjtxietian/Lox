//package com.craftinginterpreters.lox;
//
//import java.util.List;
//
//// Creates an unambiguous, if ugly, string representation of AST nodes.
//class AstPrinter implements Expr.Visitor<String> , Stmt.Visitor<String>  {
//    String print(Expr expr) {
//        return expr.accept(this);
//    }
//
//    @Override
//    public String visitVariableExpr(Expr.Variable expr){
//        return expr.name.toString();
//    }
//
//    @Override
//    public String visitThisExpr(Expr.This expr) {
//        return expr.keyword.toString();
//    }
//
//    @Override
//    public String visitBlockStmt(Stmt.Block stmt) {
//        return parenthesize("Block",stmt.statements);
//    }
//
//    @Override
//    public String visitBreakStmt(Stmt.Break stmt){
//        return stmt.keyword.toString();
//    }
//
//    @Override
//    public String visitVarStmt(Stmt.Var stmt) {
//        if (stmt.initializer != null) {
//            return parenthesize("Var " + stmt.name.lexeme,stmt.initializer);
//        }
//
//        return parenthesize("Var " + stmt.name.lexeme);
//    }
//
//    @Override
//    public String visitBinaryExpr(Expr.Binary expr) {
//        return parenthesize(expr.operator.lexeme, expr.left, expr.right);
//    }
//
//    @Override
//    public String visitGroupingExpr(Expr.Grouping expr) {
//        return parenthesize("group", expr.expression);
//    }
//
//    @Override
//    public String visitLiteralExpr(Expr.Literal expr) {
//        if (expr.value == null) return "nil";
//        return expr.value.toString();
//    }
//
//    @Override
//    public String visitUnaryExpr(Expr.Unary expr) {
//        return parenthesize(expr.operator.lexeme, expr.right);
//    }
//
//    private String parenthesize(String name, Expr... exprs) {
//        StringBuilder builder = new StringBuilder();
//
//        builder.append("(").append(name);
//        for (Expr expr : exprs) {
//            builder.append(" ");
//            builder.append(expr.accept(this));
//        }
//        builder.append(")");
//
//        return builder.toString();
//    }
//
//    private String parenthesize(String name, Stmt... stmts) {
//        StringBuilder builder = new StringBuilder();
//
//        builder.append("(").append(name);
//        for (Stmt s : stmts) {
//            builder.append(" ");
//            builder.append(s.accept(this));
//        }
//        builder.append(")");
//
//        return builder.toString();
//    }
//
//    private String parenthesize(String name, List<Stmt> statements) {
//        StringBuilder builder = new StringBuilder();
//
//        builder.append("(").append(name);
//        for (Stmt s : statements) {
//            builder.append(" ");
//            builder.append(s.accept(this));
//        }
//        builder.append(")");
//
//        return builder.toString();
//    }
//
//
//    //test
//    public static void main(String[] args) {
//        Expr expression = new Expr.Binary(
//                new Expr.Unary(
//                        new Token(TokenType.MINUS, "-", null, 1),
//                        new Expr.Literal(123)),
//                new Token(TokenType.STAR, "*", null, 1),
//                new Expr.Grouping(
//                        new Expr.Literal(45.67)));
//
//        System.out.println(new AstPrinter().print(expression));
//    }
//}