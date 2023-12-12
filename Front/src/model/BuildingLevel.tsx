interface BuildingComponent{}
class BuildingLevel implements BuildingComponent{
    name: string | null;
    components: BuildingComponent[];
    constructor(name: string | null, components: BuildingComponent[]){
        this.name = name;
        this.components = components;
    }
    addComponent(component: BuildingComponent){
        this.components.push(component);
    }
}

export type{ BuildingComponent };
export { BuildingLevel };