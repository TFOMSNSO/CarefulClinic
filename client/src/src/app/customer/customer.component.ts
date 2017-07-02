import { Component, OnInit } from '@angular/core';
import { Validators, FormGroup, FormArray, FormBuilder } from '@angular/forms';
import { Customer } from '../model/customer.interface';

@Component({
    moduleId: module.id,
    selector: 'customer-component',
    templateUrl: 'customer.component.html',
})
export class CustomerComponent implements OnInit {
    public myForm: FormGroup; // our form model

    // we will use form builder to simplify our syntax
    constructor(private _fb: FormBuilder) { }

    ngOnInit() {
    // we will initialize our form here
    this.myForm = this._fb.group({
            name: ['', [Validators.required, Validators.minLength(5)]],
            addresses: this._fb.array([
                this.initAddress(),
            ])
        });
    }

    save(model: Customer) {
        // call API to save customer
        console.log(model);
    }
    
    initAddress() {
        // initialize our address
        return this._fb.group({
            street: ['', Validators.required],
            postcode: ['']
        });
    }
    
    addAddress() {
    // add address to the list
    const control = <FormArray>this.myForm.controls['addresses'];
    control.push(this.initAddress());
	}
	
	removeAddress(i: number) {
    // remove address from the list
    const control = <FormArray>this.myForm.controls['addresses'];
    control.removeAt(i);
	}
    
}