package com.craftinginterpreters.lox;

import java.util.List;

// Creates an unambiguous, if ugly, string representation of AST nodes.
class AstPrinter implements Expr.Visitor<String>, Stmt.Visitor<String> {
    String print(Expr expr) {
        return expr.accept(this);
    }

    String print(List<Stmt> statements){
        StringBuilder sb = new StringBuilder();

        for(Stmt s : statements){
             sb.append(s.accept(this));
        }

        return sb.toString();
    }

    @Override
    public String visitThisExpr(Expr.This expr) {
        return expr.keyword.toString();
    }

    @Override
    public String visitBlockStmt(Stmt.Block stmt) {
        return parenthesize("Block", stmt.statements);
    }

    @Override
    public String visitBreakStmt(Stmt.Break stmt) {
        return stmt.keyword.toString();
    }

    @Override
    public String visitVarStmt(Stmt.Var stmt) {
        if (stmt.initializer != null) {
            return parenthesize("Var " + stmt.name.lexeme, stmt.initializer);
        }

        return "( Var " + stmt.name.lexeme + ")";
    }

    @Override
    public String visitVariableExpr(Expr.Variable expr) {
        return expr.name.lexeme;
    }

    @Override
    public String visitAssignExpr(Expr.Assign expr) {
        return parenthesize("Assign " + expr.name.lexeme, expr.value);
    }

    @Override
    public String visitFunctionStmt(Stmt.Function stmt) {

        StringBuilder builder = new StringBuilder();

        builder.append(stmt.name.lexeme).append(" paras:");
        for (Token t : stmt.params) {
            builder.append(" ");
            builder.append(t.lexeme);
        }

        builder.append(" body : ");

        return parenthesize(builder.toString(), stmt.body);
    }

    @Override
    public String visitExpressionStmt(Stmt.Expression stmt) {
        return print(stmt.expression);
    }

    @Override
    public String visitIfStmt(Stmt.If stmt) {
        String c = print(stmt.condition);
        return parenthesize("If " + c, stmt.thenBranch, stmt.elseBranch);
    }

    @Override
    public String visitClassStmt(Stmt.Class stmt) {
        String s;

        if(stmt.superclass != null){
            s = "";
        }else{
            s = print(stmt.superclass);
        }


        String m = " Methods : ";
        for(Stmt.Function f : stmt.methods){
            m.concat(f.toString() + ' ');
        }

        return "( Class"+stmt.name.lexeme + " superclass :" + s + m + ")";
    }

    @Override
    public String visitSuperExpr(Expr.Super expr) {
        return expr.keyword.lexeme;
    }

    @Override
    public String visitGetExpr(Expr.Get expr) {
        return parenthesize("Get" + expr.name.lexeme + " " , expr.object);
    }

    @Override
    public String visitSetExpr(Expr.Set expr) {
        return parenthesize("Set",expr.object , expr.value);
    }

    @Override
    public String visitPrintStmt(Stmt.Print stmt) {
        return parenthesize("Print",stmt.expression);
    }

    @Override
    public String visitReturnStmt(Stmt.Return stmt) {
       return parenthesize("Return",stmt.value);
    }

    @Override
    public String visitWhileStmt(Stmt.While stmt) {
        return parenthesize("While" + print(stmt.condition),stmt.body);
    }

    @Override
    public String visitCallExpr(Expr.Call expr) {
        StringBuilder s = new StringBuilder();

        s.append("Call ").append(expr.paren.lexeme).append(" " + print(expr.callee));
        for(Expr e : expr.arguments){
            s.append(print(e));
        }

        return s.toString();
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return parenthesize(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return parenthesize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    private String parenthesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expr expr : exprs) {
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }

    private String parenthesize(String name, Stmt... stmts) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Stmt s : stmts) {
            builder.append(" ");
            builder.append(s.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }

    private String parenthesize(String name, List<Stmt> statements) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Stmt s : statements) {
            builder.append(" ");
            builder.append(s.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }

    @Override
    public String visitLogicalExpr(Expr.Logical expr) {
        return parenthesize(expr.operator.lexeme , expr.left,expr.right);
    }

    //test
    public static void main(String[] args) {
        Expr expression = new Expr.Binary(
                new Expr.Unary(
                        new Token(TokenType.MINUS, "-", null, 1),
                        new Expr.Literal(123)),
                new Token(TokenType.STAR, "*", null, 1),
                new Expr.Grouping(
                        new Expr.Literal(45.67)));

        System.out.println(new AstPrinter().print(expression));
    }
}