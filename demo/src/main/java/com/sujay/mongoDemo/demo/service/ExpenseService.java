package com.sujay.mongoDemo.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sujay.mongoDemo.demo.model.Expense;
import com.sujay.mongoDemo.demo.repository.ExpenseRepository;

@Service
public class ExpenseService {
	
	private ExpenseRepository expenseRepository;
	
	public ExpenseService(ExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}

	public void addExpense(Expense expense) {
		expenseRepository.insert(expense);
	}
	
	public void updateExpense(Expense expense) {
		Expense savedExpense = expenseRepository.findById(expense.getId())
				.orElseThrow(() -> new RuntimeException(
						String.format("Cannot find Expense by ID%s", expense.getId())));
		
		savedExpense.setExpenseName(expense.getExpenseName());
		savedExpense.setExpenseCategory(expense.getExpenseCategory());
		savedExpense.setExpenseAmount(expense.getExpenseAmount());
		
		expenseRepository.save(savedExpense);
	}
	
	public List<Expense> getAllExpenses() {
		return expenseRepository.findAll();
	}
	
	public Expense getExpenseByName(String name) {
		return expenseRepository.findByName(name)
				.orElseThrow(() -> new RuntimeException(
						String.format("Cannot find Expense by Name%s", name)));
	}
	
	public void deleteExpense(String id) {
		expenseRepository.deleteById(id);
	}
	
}
