"use strict";(self.webpackChunkangular=self.webpackChunkangular||[]).push([[198],{1198:(g,c,o)=>{o.r(c),o.d(c,{SecretModule:()=>h});var l=o(9808),a=o(5358),i=o(2340),e=o(1223),u=o(520),m=o(4981);const p=[{path:"",component:(()=>{class t{constructor(n,s){this.httpClient=n,this.serv=s}ngOnInit(){this.httpClient.get(`${i.N.host}/secret`,{responseType:"text"}).subscribe(n=>{n=String(n),console.log(n),this.flag=this.serv.getUsers()})}}return t.\u0275fac=function(n){return new(n||t)(e.Y36(u.eN),e.Y36(m.K))},t.\u0275cmp=e.Xpm({type:t,selectors:[["app-main"]],decls:3,vars:1,consts:[[1,"container","mt-5",2,"background-color","darkgray"]],template:function(n,s){1&n&&(e.TgZ(0,"div",0)(1,"h1"),e._uU(2),e.qZA()()),2&n&&(e.xp6(2),e.hij(" ",s.flag," "))},styles:[""]}),t})()}];let d=(()=>{class t{}return t.\u0275fac=function(n){return new(n||t)},t.\u0275mod=e.oAB({type:t}),t.\u0275inj=e.cJS({imports:[[a.Bz.forChild(p)],a.Bz]}),t})(),h=(()=>{class t{}return t.\u0275fac=function(n){return new(n||t)},t.\u0275mod=e.oAB({type:t}),t.\u0275inj=e.cJS({imports:[[l.ez,d]]}),t})()}}]);